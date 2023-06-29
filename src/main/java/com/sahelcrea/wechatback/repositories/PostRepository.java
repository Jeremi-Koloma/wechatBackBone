package com.sahelcrea.wechatback.Repositories;

import com.sahelcrea.wechatback.Models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    // une methode pour avoir la liste des utilisateurs à ordre recent
    public List<Posts> findAll();


    // une méthode pour avoir la liste des utilisateur par recherche
//    public List<Posts> findByUsername(@Param("username") String username);


    // une méthode pour avoir un post par son ID
    public Posts findPostsById(@Param("x") Long postid);


    @Modifying
    public void deletePostsById(@Param("x") Long postid);
}
