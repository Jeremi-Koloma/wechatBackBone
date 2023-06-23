package com.sahelcrea.wechatback.services;

import com.sahelcrea.wechatback.models.AppRole;
import com.sahelcrea.wechatback.models.AppUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    // une méthode pour enregistrer un utilisateur
    public void saveUser(AppUser appUser);


    // une méthode pour trouver un utilisateur par son username
    public AppUser findByUsername(String username);


    // une méthode pour trouver un User par son Email
    public AppUser findByEmail(String email);


    // une méthode pour avoir la liste des utilisateurs
    public List<AppUser> userList();


    // une méthode qui permet d'ajouter des nouveaux rôles dans la base de donnée
    public AppRole addNewRole (AppRole appRole);


    // Une méthode  qui permet d'affecter un rôle à un utilisateur
    public void addRoleToUser(String username, String roleName);


    // une méthode pour retrouver le role d'un utilisateur
    public AppRole findUserRoleByName(String name);



    // une méthode pour enregister le role
    public AppRole saveRole(AppRole appRole);


    // une méthode pour pouvoir modifier un utilsateur
    public void updateUser(AppUser appUser);


    // une méthode pour avoir un utilisateur par son id
    public AppUser findUserById(Long id);


    // une méthode pour supprimer un utilisateur
    String supprimer(Long idUser);


    // Une méthode pour Changer le mots de passe d'un utilisateur
    public void updateUserPassword(AppUser appUser);



    // une méthode pour le mots de passe oublier
    public void ressetPassword(AppUser appUser);


    // une méthode pour mèttre une photo de profil à jour
    public String saveUserImage(MultipartFile multipartFile, Long userImageId);



    // une méthode pour retrouver la liste des utilisateur par son username lors de recherche
    public List<AppUser> getUserListByUsername(String username);


    public void simpleSave(AppUser appUser);



}
