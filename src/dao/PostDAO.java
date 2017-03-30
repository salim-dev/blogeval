/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.PostModel;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class PostDAO extends DAO<PostModel, Long>{

    public PostDAO() {
        super();
    }

    
    @Override
    public PostModel create(PostModel post) {
      PostModel postCreate = new PostModel();
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO posts ( "
                        + "title,\n"
                        + " content,\n"
                        + " author_id\n"
                        + ") VALUES (?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setString(1, post.getTitle());
                pst.setString(2, post.getContent());
                pst.setLong(3, post.getAuthor_id());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    // get generate key
                    ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in post object
                        post.setId(id_increment.getLong(1));
                        // assign object post in object return
                        postCreate = post;
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return postCreate;
            }

        } else {
            return postCreate;
        }

        return postCreate;
        
        
    }

    @Override
    public boolean update(PostModel post) {
   boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update posts set"
                        + " title = ?,\n"
                        + " content = ?,\n"
                        + " author_id = ?,\n"
                        + " update_at = ?\n"
                        + " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, post.getTitle());
                pst.setString(2, post.getContent());
                pst.setLong(3, post.getAuthor_id());
                pst.setString(4, post.getUpdate_at());
                pst.setLong(5, post.getId());
                // excute update row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;
    }

    @Override
    public boolean delete(Long primary_key) {
  boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM posts WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, primary_key);
                // excute delete row in table
                int insert = pst.executeUpdate();
                // if delete in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success; 
    }

    @Override
    public ArrayList<PostModel> getAll() {
   // create array list post empty
        ArrayList<PostModel> listPost = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM posts";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all posts in array object post

                while (rs.next()) {
                    PostModel el = new PostModel(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("create_at"),
                                    rs.getString("update_at"),
                            rs.getLong("author_id")
                    );
                    listPost.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listPost;
            }

        } else {
            return listPost;
        }

        return listPost;
    }

    @Override
    public PostModel find(Long primary_key) {
 // create array post empty
        PostModel post = new PostModel();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM posts WHERE id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert posts in object                   

                    post.setId(rs.getLong("id"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setCreate_at(rs.getString("create_at"));
                       post.setUpdate_at(rs.getString("update_at"));
                    post.setAuthor_id(rs.getLong("author_id"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return post;
            }

        } else {
            return post;
        }

        return post;


    }

    @Override
    public boolean isValid(PostModel post) {
        boolean isValid = true;

        // if id is empty
        if (post.getId() == 0) {
            isValid = false;
        }

        return isValid;
    }
    
}
