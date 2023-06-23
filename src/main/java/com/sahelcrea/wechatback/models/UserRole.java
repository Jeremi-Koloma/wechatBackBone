package com.sahelcrea.wechatback.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor // Génération d'un constructeur sans paramètre
@Getter // Génération des Getters
@Setter // Génération des setters
@Entity // Annotation JPA pour dire que cette classe sera une table dans la base de donnée
public class UserRole {
    @Id // Annotation JPA pour identifier notre id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotaion JPA Notre primary KEY
    //@Column(updatable = false, nullable = false)
    private Long userRoleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private AppRole appRole;


    public UserRole(Long userRoleId, AppUser appUser, AppRole appRole) {
        this.userRoleId = userRoleId;
        this.appUser = appUser;
        this.appRole = appRole;
    }

    public UserRole(AppUser appUser, AppRole appRole) {
        this.appUser = appUser;
        this.appRole = appRole;
    }

}
