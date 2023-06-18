package com.sahelcrea.wechatback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor // Génération d'un constructeur avec tous les paramètres
@NoArgsConstructor // Génération d'un constructeur sans paramètre
@Getter // Génération des Getters
@Setter // Génération des Setters
@Entity // Annotation JPA pour dire que cette classe sera une table dans la base de donnée
public class AppRole {
    @Id // une annotation JPA pour identifier notre id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation JPA pour identifier notre PK
    @Column(updatable = false, nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "appRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();
}
