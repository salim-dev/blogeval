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
import model.CommentModel;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class CommentDAO extends DAO<CommentModel, Long>{

    public CommentDAO() {
        super();
    }

    
    @Override
    public CommentModel create(CommentModel comment) {
      CommentModel commentCreate = new CommentModel();
        if (this.bddmanager.connect()) {

            try {

              // create requete 
                String requete = "INSERT INTO comments ( "
                        + "user_id,\n"
                        + " post_id,\n"
                        + " comment\n"
                        + ") VALUES (?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setLong(1, comment.getUser_id());
                pst.setLong(2, comment.getPost_id());
                pst.setString(3, comment.getComment());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) { 
                     ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in post object
                        comment.setId(id_increment.getLong(1));
                        // assign object post in object return
                        commentCreate = comment;
                    }       

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return commentCreate;
            }

        } else {
            return commentCreate;
        }

        return commentCreate;
        
        
    }

    @Override
    public boolean update(CommentModel comment) {
   boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update comments set"
                        + " comment = ?,\n"
                        + " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, comment.getComment());
                pst.setLong(2, comment.getId());
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
                String requete = "DELETE FROM comments WHERE id = ?";
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
    public ArrayList<CommentModel> getAll() {
   // create array list comment empty
        ArrayList<CommentModel> listComment = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM comments";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all comments in array object comment

                while (rs.next()) {
                    CommentModel el = new CommentModel();
                    el.setId(rs.getLong("id"));
                    el.setUser_id(rs.getLong("user_id"));
                    el.setPost_id(rs.getLong("post_id"));
                    el.setComment(rs.getString("comment"));              
                    listComment.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listComment;
            }

        } else {
            return listComment;
        }

        return listComment;
    }

    @Override
    public CommentModel find(Long primary_key) {
 // create array comment empty
        CommentModel comment = new CommentModel();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM comments WHERE id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert comments in object                   

                    comment.setId(rs.getLong("id"));
                    comment.setUser_id(rs.getLong("user_id"));
                    comment.setPost_id(rs.getLong("post_id"));
                    comment.setComment(rs.getString("comment"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return comment;
            }

        } else {
            return comment;
        }

        return comment;


    }

    @Override
    public boolean isValid(CommentModel comment) {
        boolean isValid = true;

        // if id is empty
        if (comment.getId() == 0) {
            isValid = false;
        }

        return isValid;
    }
    
}
