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
import model.UserModel;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class UserDAO extends DAO<UserModel, Long>{

    @Override
    public UserModel create(UserModel user) {

        UserModel userCreate = new UserModel();
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO users ( "
                        + "pseudo,\n"
                        + "password,\n"
                        + "firstname,\n"
                        + " lastname,\n"
                        + " mail,\n"
                        + " is_admin\n"
                        + ") VALUES (?,?,?,?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setString(1, user.getPseudo());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getFirstname());
                pst.setString(4, user.getLastname());
                pst.setString(5, user.getMail());
                pst.setBoolean(6, user.isIsAdmin());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    // get generate key
                    ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in user object
                        user.setId(id_increment.getLong(1));
                        // assign object user in object return
                        userCreate = user;
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return userCreate;
            }

        } else {
            return userCreate;
        }

        return userCreate;
    }

    @Override
    public boolean update(UserModel user) {
 boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update users set"
                        + " pseudo = ?,\n"
                        + " firstname = ?,\n"
                        + " lastname = ?,\n"
                        + " mail = ?,\n"
                        + " is_admin = ?\n";

                if (!user.getPassword().isEmpty()) {
                    requete += " ,password = MD5(?)\n";
                }

                requete += " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, user.getFirstname());
                pst.setString(2, user.getLastname());
                pst.setString(1, user.getFirstname());
                pst.setString(2, user.getLastname());
                pst.setString(3, user.getMail());
                pst.setBoolean(4, user.isIsAdmin());

                if (!user.getPassword().isEmpty()) {
                    pst.setString(5, user.getPassword());
                    pst.setLong(6, user.getId());
                } else {
                    pst.setLong(5, user.getId());
                }

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
                String requete = "DELETE FROM users WHERE id = ?";
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
    public ArrayList<UserModel> getAll() {
   // create array list user empty
        ArrayList<UserModel> listUser = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM users";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all users in array object user

                while (rs.next()) {
                    UserModel el = new UserModel();
                    el.setId(rs.getLong("id"));
                    el.setFirstname(rs.getString("pseudo"));
                    el.setFirstname(rs.getString("firstname"));
                    el.setLastname(rs.getString("lastname"));
                    el.setMail(rs.getString("mail"));
                    el.setIsAdmin(rs.getBoolean("is_admin"));   
                    listUser.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listUser;
            }

        } else {
            return listUser;
        }

        return listUser;
    }

    @Override
    public UserModel find(Long primary_key) {
 // create array user empty
        UserModel user = new UserModel();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM users WHERE id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert users in object                   

                    user.setId(rs.getLong("id"));
                    user.setFirstname(rs.getString("pseudo"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setMail(rs.getString("mail"));
                    user.setIsAdmin(rs.getBoolean("is_admin"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return user;
            }

        } else {
            return user;
        }

        return user;
    }

    @Override
    public boolean isValid(UserModel user) {
        boolean isValid = true;

               // if id is empty
               if (user.getId() == 0) {
                   isValid = false;
               }

               return isValid;
    }
    
}
