package com.sahelcrea.wechatback.repositories;

import com.sahelcrea.wechatback.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Cette classe va étendre de JpaRepository avec Entity en param et son id
@Repository // Annotation bean
public interface CommentRepository extends JpaRepository<Comments, Long> {
}
