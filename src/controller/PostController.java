/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PostDAO;
import dao.UserDAO;
import java.util.ArrayList;
import model.PostModel;
import view.PostItemView;
import view.PostListView;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class PostController {

    private PostDAO postDAO;
    private UserDAO currentUser;

    public PostController(PostDAO postDAO) {
        this.postDAO = postDAO;
        this.currentUser = new UserDAO();
    }

    public void listPost(PostListView postListView, PostItemView postItemView) {

        ArrayList<PostModel> list_posts = this.postDAO.getAll();

        Object rowData[] = new Object[11];

        for (PostModel post : list_posts) {
            postItemView.getTitle().setText(post.getTitle());
            postItemView.getdate().setText(post.getCreate_at());
            postItemView.getAuthor().setText("Mr Jhon");
            postListView.add(postItemView);
        }

    }

}
