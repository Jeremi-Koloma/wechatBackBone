package com.sahelcrea.wechatback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor // Génération d'un constructeur sans paramètre
@AllArgsConstructor // Génération d'un constructeur avec tous les paramètre
@Getter // Génération des Getters
@Setter // Génération des Setters
@Entity // Annotation JPA pour dire que cette classe sera une table dans la base de donnée
public class Comments {
    @Id // Annotation JPA pour identifier notre ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour Identifier notre primary Key
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    private String username;
    private Date postedDate;
}
