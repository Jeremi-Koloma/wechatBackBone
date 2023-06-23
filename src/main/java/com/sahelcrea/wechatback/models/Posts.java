package com.sahelcrea.wechatback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor // Génération d'un constructeur sans parmètre
@AllArgsConstructor // Génération d'un constructeur avec tous les paramètre
@Getter // Génération des Getters
@Setter // Génération des Setters
@Entity // Annotation JPA pour dire que cette classe sera une table dans la base de donnée
public class Posts implements Serializable {
    @Serial
    private static final long serialVersionUID = 34546474787L;

    @Id // Annotation JPA pour definir notre id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation JPA pour definir notre Primary KEY
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    private String caption;
    private String location;
    private String likes;
    private Date postedDate;
    private Integer userImageId;
    private String postImageName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Comments> comments;
}
