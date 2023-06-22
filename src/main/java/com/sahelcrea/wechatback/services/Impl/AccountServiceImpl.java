package com.sahelcrea.wechatback.services.Impl;

import com.sahelcrea.wechatback.models.AppRole;
import com.sahelcrea.wechatback.models.AppUser;
import com.sahelcrea.wechatback.repositories.AppRoleRepository;
import com.sahelcrea.wechatback.repositories.AppUserRepository;
import com.sahelcrea.wechatback.services.AccountService;
import com.sahelcrea.wechatback.utility.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Cette classe va implémenté notre interface AccountService pour bénéficier les méthodes
@Service // Pour dire qu'il s'agit de la logique métier
public class AccountServiceImpl implements AccountService {

    // Injections des dépendance

    // Injectons BCryptPasswordEncoder pour encoder le mots de passe de l'utlisateur avant dans l'enregister dans la base
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // Injectons AppUserRepository Pour enregister l'utilisateur nous avons besoin de son Repository
    @Autowired
    private AppUserRepository appUserRepository;


    // Injectons AppRoleRepository Pour enregister les Roles aussi nous avons besoin de son Repository
    @Autowired
    private AppRoleRepository appRoleRepository;


    // Injectons nous avons besoin de notre Emailconstructeur pour choisir un type de mail
    @Autowired
    private EmailConstructor emailConstructor;


    // Injectons JavaMailSender pour envoyer le mail
    @Autowired
    private JavaMailSender javaMailSender;




    @Override
    public void saveUser(AppUser appUser) {

    }

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findByEmail(String email) {
        return null;
    }

    @Override
    public List<AppUser> userList() {
        return null;
    }

    @Override
    public AppRole findUserRoleByName(String role) {
        return null;
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return null;
    }

    @Override
    public void updateUser(AppUser appUser) {

    }

    @Override
    public AppUser findById(AppUser appUser) {
        return null;
    }

    @Override
    public void deleteUser(AppUser appUser) {

    }

    @Override
    public void ressetPassword(AppUser appUser) {

    }

    @Override
    public List<AppUser> getUserListByUsername(String username) {
        return null;
    }

    @Override
    public void simpleSave(AppUser appUser) {

    }
}
