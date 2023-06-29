package com.sahelcrea.wechatback.Services;

import com.sahelcrea.wechatback.Models.Posts;

public interface CommentService {
    // une méthode pour faire de commentaire
    public void saveComment(Posts posts, String username, String comment);


    // une méthode pour supprimer un commentaire
    String supprimer(Long idComment);
}
