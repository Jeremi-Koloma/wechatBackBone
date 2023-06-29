package com.sahelcrea.wechatback.Services;

import com.sahelcrea.wechatback.Models.AppUser;
import com.sahelcrea.wechatback.Models.Posts;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface PostService {
    // une methode pour enregister un post, mais en connaissant l'utilisateur
    public Posts savePost(AppUser appUser, HashMap<String, String> request, String postImageName);


    // une méthode pour avoir la liste de tous les post
    public List<Posts> postList();


    // une méthode pour retourner un post par son id
    public Posts getPostById(Long id);


    // une méthode pour avoir la liste des post d'un utilisateur par son username
    //public List<Posts> findPostByUsername(String username);


    // une méthode pour supprimer un post
    public Posts deletePost(Posts posts);


    // une méthode pour enregister la photo du post dans le serveur
    public String savePostImage(HttpServletRequest request, String fileName);
}
