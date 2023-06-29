package com.sahelcrea.wechatback.Repositories;

import com.sahelcrea.wechatback.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Cette classe va étendre de JpaRepository avec Entity en param et son id
@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    @Modifying
    public void deleteCommentById(@Param("x") Long commentId);
}
