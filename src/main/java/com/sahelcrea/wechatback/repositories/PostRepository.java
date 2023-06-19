package com.sahelcrea.wechatback.repositories;

import com.sahelcrea.wechatback.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Cette classe va étendre de JpaRepository
public interface PostRepository extends JpaRepository<Posts, Long> {

    // une methode pour avoir la liste des utilisateurs à ordre recent
    public List<Posts> findAll();


    // une méthode pour avoir la liste des utilisateur par recherche
    public List<Posts> findByUsername(@Param("username") String username);


    // une méthode pour avoir un post par son ID
    public Posts findPostsById(@Param("x") Long postid);


    public void deletePostsById(@Param("x") Long postid);
}
