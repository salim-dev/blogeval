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
public class CommentModel {
    private long id;
    private long user_id;
    private long post_id;
    private String comment;

    public CommentModel() {
        this.id = 0;
        this.user_id = 0;
        this.post_id = 0;
        this.comment = "";
    }

    public CommentModel(long id, long user_id, long post_id, String comment) {
        this.user_id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 41 * hash + (int) (this.post_id ^ (this.post_id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.comment);
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
        final CommentModel other = (CommentModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.post_id != other.post_id) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentModel{" + "id=" + id + ", user_id=" + user_id + ", post_id=" + post_id + ", comment=" + comment + '}';
    }

    
    
    
}
