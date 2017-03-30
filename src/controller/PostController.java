/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PostDAO;
import dao.UserDAO;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.JPanel;
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

    public JPanel listPost(JPanel jpBody) {
        ArrayList<PostModel> list_posts = this.postDAO.getAll();
        GridBagConstraints constraints = new GridBagConstraints();
        int y = 0;

        for (PostModel post : list_posts) {
            PostItemView postItemView = new PostItemView();
            postItemView.getTitle().setText(post.getTitle());
            System.out.println(post.getContent().length());
        
//            if(post.getContent().length() <= 1000){
//                  postItemView.getContent().setText(post.getContent() + " ...t");
//            }else{
//                 postItemView.getContent().setText(post.getContent().substring(0, 1000) + " ...y"); 
//            }
           postItemView.getContent().setText(post.getContent());
            postItemView.getdate().setText(post.getCreate_at());
            postItemView.getAuthor().setText("Mr Jhon");
            //constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = y;
            jpBody.add(postItemView, constraints);
            y++;
        }

        return jpBody;

    }

}
