package com.sahelcrea.wechatback.services.Impl;

import com.sahelcrea.wechatback.models.AppRole;
import com.sahelcrea.wechatback.models.AppUser;
import com.sahelcrea.wechatback.models.UserRole;
import com.sahelcrea.wechatback.repositories.AppRoleRepository;
import com.sahelcrea.wechatback.repositories.AppUserRepository;
import com.sahelcrea.wechatback.services.AccountService;
import com.sahelcrea.wechatback.utility.Constants;
import com.sahelcrea.wechatback.utility.EmailConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Cette classe va implémenté notre interface AccountService pour bénéficier les méthodes
@Service // Pour dire qu'il s'agit de la logique métier
@Transactional
public class AccountServiceImpl implements AccountService {

    // Injections des dépendance

    // Injectons BCryptPasswordEncoder pour encoder le mots de passe de l'utlisateur avant dans l'enregister dans la base
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // Injectons AppUserRepository Pour enregister l'utilisateur nous avons besoin de son Repository
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AccountService accountService;


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
        // Recupérons saisi le mots de passe de l'utilisateur
        String userPassword = appUser.getPassword();
        // Encodons le mots de passe
        String encrytedPassword = bCryptPasswordEncoder.encode(userPassword);
        // lions ce mots de passe
        appUser.setPassword(encrytedPassword);
        // Vérifier si la date de création est vide, on l'ajoute une date
        if (appUser.getCreatedDate() == null){
            appUser.setCreatedDate(new Date());
        }
        // Affectons Role "USER" à l'utilisateur
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(appUser, accountService.findUserRoleByName("USER")));
        appUser.setUserRoles(userRoles);

        // La photo de profil par defaut
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Constants.TEMP_USER.toPath());
            String fileName = appUser.getId() + ".png";
            Path path = Paths.get(Constants.USER_FOLDER + fileName);
            Files.write(path, bytes);
        }catch (IOException e){
            e.printStackTrace();
        }

        // On enregistre l'utilisateur
        appUserRepository.save(appUser);
        // on l'envoie un mail après l'inscription
        javaMailSender.send(emailConstructor.constructNewUserSigninEmail(appUser, encrytedPassword));

    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> userList() {
        return appUserRepository.findAll();
    }

    @Override // Ajouter un nouveau Rôle dans la base
    public AppRole addNewRole(AppRole appRole) {
        if (appRole.getName() != null){
            appRoleRepository.save(appRole);
        }
        else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appRole;
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        // On recupère d'abord l'utilisateur dans la base
        AppUser appUser = appUserRepository.findByUsername(username);
        // On recupère aussi le Role dans base
        AppRole appRole = appRoleRepository.findRoleByName(roleName);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(appUser,appRole));

        // Affectons maintenant le role à l'utilisateur
        appUser.setUserRoles(userRoles);
        // on enregistre l'utilisateur
        appUserRepository.save(appUser);
    }

    @Override
    public AppRole findUserRoleByName(String name) {
        return appRoleRepository.findRoleByName(name);
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void updateUser(AppUser appUser) {
        // Recupérons le mots de passe saisi par l'utilsateur
        String userPassword = appUser.getPassword();
        // Cryter ce mots de passe
        String encrytedPassword = bCryptPasswordEncoder.encode(userPassword);
        // On prend le nouveau mots de passe
        appUser.setPassword(encrytedPassword);
        // on l'enregistre
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser findUserById(Long id) {
        return  appUserRepository.findUserById(id);
    }

    @Override
    public String supprimer(Long idUser) {
        appUserRepository.deleteById(idUser);
        return "Utilisateur supprimer";
    }

    @Override
    public void updateUserPassword(AppUser appUser) {
        String password = appUser.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        appUser.setPassword(encodePassword);
        appUserRepository.save(appUser);
    }


    @Override
    public void ressetPassword(AppUser appUser) {
        // Générons un mots de passe aléatoir
        String password = RandomStringUtils.randomAlphanumeric(10);
        // Encodons ce mots de passe généré
        String encrytedPassword = bCryptPasswordEncoder.encode(password);
        // On change le mots de passe
        appUser.setPassword(encrytedPassword);
        // on l'enregistre
        appUserRepository.save(appUser);
        // On l'envoie mail de mots de passe oublié
        javaMailSender.send(emailConstructor.constructRessetPasswordEmail(appUser, password));
    }

    @Override
    public String saveUserImage(MultipartFile multipartFile, Long userImageId) {
        byte[] bytes;
        try {
            Files.deleteIfExists(Paths.get(Constants.USER_FOLDER + "/" + userImageId + ".png"));
            bytes = multipartFile.getBytes();
            Path path = Paths.get(Constants.USER_FOLDER + userImageId + ".png");
            Files.write(path, bytes);
            return "Photo de profil enregister avec succès !";
        } catch (IOException e) {
            return "User picture Saved";
        }
    }

    @Override
    public List<AppUser> getUserListByUsername(String username) {
        return appUserRepository.findByUsernameContaining(username);
    }

    @Override
    public void simpleSave(AppUser appUser) {
        appUserRepository.save(appUser);
    }
}
