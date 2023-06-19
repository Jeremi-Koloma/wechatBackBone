package com.sahelcrea.wechatback.services;

import com.sahelcrea.wechatback.models.AppRole;
import com.sahelcrea.wechatback.models.AppUser;

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


    // une méthode pour retrouver le role d'un utilisateur
    public AppRole findUserRoleByName(String role);


    // une méthode pour enregister le role
    public AppRole saveRole(AppRole appRole);


    // une méthode pour pouvoir modifier un utilsateur
    public void updateUser(AppUser appUser);


    // une méthode pour avoir un utilisateur par son id
    public AppUser findById(AppUser appUser);


    // une méthode pour supprimer un utilisateur
    public void deleteUser(AppUser appUser);


    // une méthode pour le mots de passe oublier
    public  void  ressetPassword(AppUser appUser);


    // une méthode pour retrouver la liste des utilisateur par son username lors de recherche
    public List<AppUser> getUserListByUsername(String username);


    public void simpleSave(AppUser appUser);



}
