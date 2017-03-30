/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class UserModel {
    private long id;
    private String pseudo;
    private String password;
    private String lastname;
    private String firstname;
    private boolean isAdmin;
    private String mail;

    public UserModel() {
        this.id = 0;
        this.pseudo = "";
        this.password = "";
        this.lastname = "";
        this.firstname = "";
        this.isAdmin = false;
        this.mail = "";
    }

    public UserModel(long id, String pseudo, String password, String lastname, String firstname, boolean isAdmin, String mail) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.isAdmin = isAdmin;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 13 * hash + Objects.hashCode(this.pseudo);
        hash = 13 * hash + Objects.hashCode(this.password);
        hash = 13 * hash + Objects.hashCode(this.lastname);
        hash = 13 * hash + Objects.hashCode(this.firstname);
        hash = 13 * hash + (this.isAdmin ? 1 : 0);
        hash = 13 * hash + Objects.hashCode(this.mail);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserModel other = (UserModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (!Objects.equals(this.pseudo, other.pseudo)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserModel{" + "id=" + id + ", pseudo=" + pseudo + ", password=" + password + ", lastname=" + lastname + ", firstname=" + firstname + ", isAdmin=" + isAdmin + ", mail=" + mail + '}';
    }
    
    
}
