/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogeval;

import controller.PostController;
import dao.PostDAO;
import javax.swing.JFrame;
import view.PostListView;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class BlogEval {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              JFrame frame = new JFrame();
              
                      PostDAO postDAO = new PostDAO();
        PostController postController = new PostController(postDAO);
        PostListView postListView =  new PostListView(postController);
              
                   frame.setSize(1000, 691);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLocation(450, 110);
        
        frame.add(postListView);
    }
    
}
