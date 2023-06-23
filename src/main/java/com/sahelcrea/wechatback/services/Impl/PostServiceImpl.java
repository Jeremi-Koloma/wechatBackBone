package com.sahelcrea.wechatback.services.Impl;

import com.sahelcrea.wechatback.models.AppUser;
import com.sahelcrea.wechatback.models.Posts;
import com.sahelcrea.wechatback.repositories.PostRepository;
import com.sahelcrea.wechatback.services.PostService;
import com.sahelcrea.wechatback.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// cette classe va implémenté notre interface PostService pour bénéficier les méthodes
@Service
@Transactional
public class PostServiceImpl implements PostService {
    // Injections des dépendances
    @Autowired
    public PostRepository postRepository;



    @Override
    public Posts savePost(AppUser appUser, HashMap<String, String> request, String postImageName) {
        String caption = request.get("caption");
        String location = request.get("location");
        String content = request.get("content");
        String likes = request.get("likes");

        Posts posts = new Posts();

        posts.setCaption(caption);
        posts.setLocation(location);
        posts.setContent(content);
        posts.setPostedDate(new Date());
        posts.setLikes(likes);
        posts.setUserImageId(Math.toIntExact(appUser.getId()));
        posts.setPostImageName(postImageName);
        appUser.setPosts(posts);
        postRepository.save(posts);
        return posts;
    }

    @Override
    public List<Posts> postList() {
        return postRepository.findAll();
    }

    @Override
    public Posts getPostById(Long id) {
        return postRepository.findPostsById(id);
    }

    @Override
    public List<Posts> findPostByUsername(String username) {
        return postRepository.findByUsername(username);
    }

    @Override
    public Posts deletePost(Posts posts) {
        // En supprimant un post, on supprime aussi l'image dans le dossier
        try {
            Files.deleteIfExists(Paths.get(Constants.POST_FOLDER + "/" + posts.getPostImageName() + ".png"));
            postRepository.deletePostsById(posts.getId());
            return posts;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String savePostImage(HttpServletRequest request, String fileName) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        MultipartFile multipartFile = multipartHttpServletRequest .getFile(iterator.next());
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(Constants.POST_FOLDER + fileName + ".png");
            Files.write(path,bytes, StandardOpenOption.CREATE);
        }catch (Exception e){
            System.out.println("------ Une erreur s'est produite. La photo n'a pas été enregistrée ! ---");
            return "Une erreur s'est produite. La photo n'a pas été enregistrée !";
        }
        System.out.println("--- Photo Enregister ---");
        return "Photo Enregister !";
    }
}
