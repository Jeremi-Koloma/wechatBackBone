package com.sahelcrea.wechatback.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor // Génération d'un constructeur sans paramètre
@AllArgsConstructor // Génération d'un constructeur avec tous les paramètre
@Getter // Génération des Getters
@Setter // Génération des setters
@Entity // Annotation JPA pour dire que cette classe sera une table dans la base de donnée
public class UserRole {
    @Id // Annotation JPA pour identifier notre id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotaion JPA Notre primary KEY
    //@Column(updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppRole appRole;
}
