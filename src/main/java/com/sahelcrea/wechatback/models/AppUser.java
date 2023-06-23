package com.sahelcrea.wechatback.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // Annotation JPA pour dire que cette classe sera une table dans la BD
public class AppUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 164669782975869L;

    @Id // Annotation JPA pour Identifier notre primary KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation JPA notre Primary KEY
    @Column(updatable = false, nullable = false)
    private Long id;

    private String firstName;
    private String lastName;

    @Column (unique = true)
    private String username;

    private String password;

    @Column (unique = true)
    private String email;
    private Date createdDate;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Posts> posts;

    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<Posts> likedPost;


    // Constructeur sans argument
    public AppUser() { }


    // Constructeur avec tous les arguments
    public AppUser(Long id, String firstName, String lastName, String username, String password, String email, Date createdDate, Set<UserRole> userRoles, List<Posts> posts, List<Posts> likedPost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = createdDate;
        this.userRoles = userRoles;
        this.posts = posts;
        this.likedPost = likedPost;
    }

    // Génération des Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts.add(posts);
    }

    public List<Posts> getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(List<Posts> likedPost) {
        this.likedPost = likedPost;
    }
}
