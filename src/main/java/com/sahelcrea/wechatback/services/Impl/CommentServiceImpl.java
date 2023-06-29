package com.sahelcrea.wechatback.Services.Impl;

import com.sahelcrea.wechatback.Models.Comments;
import com.sahelcrea.wechatback.Models.Posts;
import com.sahelcrea.wechatback.Repositories.CommentRepository;
import com.sahelcrea.wechatback.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

// Cette classe va implementer notre interface CommentServie
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    // Injections des dépendance
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public void saveComment(Posts posts, String username, String comment) {
        Comments comments = new Comments();
        comments.setContent(comment);
        comments.setUsername(username);
        comments.setPostedDate(new Date());
        posts.setComments((List<Comments>) comments);
        commentRepository.save(comments);
    }

    @Override
    public String supprimer(Long idComment) {
        commentRepository.deleteCommentById(idComment);
        return "Commentaire Supprimer";
    }
}
