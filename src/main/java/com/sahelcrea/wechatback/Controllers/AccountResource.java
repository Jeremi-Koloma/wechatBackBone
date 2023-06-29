package com.sahelcrea.wechatback.Controllers;

import com.sahelcrea.wechatback.Models.AppUser;
import com.sahelcrea.wechatback.Services.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController // pour dire qu'il sagit d'un controller
@RequestMapping(value = "api/user")
public class AccountResource {
    // Injections des dépendances
    private Long userImageId;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // API Rest pour avoir la liste des utilisateurs
    @GetMapping("/list")
    public ResponseEntity<?> getUserList(){
        List<AppUser> users = accountService.userList();
        // vérifier si la liste est vide ou pas
        if (users.isEmpty()){
            return new ResponseEntity<>("UsersListEmpty", HttpStatus.OK);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    // API Rest pour avoir un utilisateur par son nom
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable("username") String username){
        AppUser user = accountService.findByUsername(username);
        // vérifions si l'utilisateur existe
        if (user == null){
            return new ResponseEntity<>("UserNotExist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    // API Rest pour avoir la liste des utilisateurs par leurs usernames
    @GetMapping("/findUserListByUsername{username}")
    public ResponseEntity<?> getUserListByUsername(@PathVariable("username") String username){
        List<AppUser> users = accountService.getUserListByUsername(username);
        // Vérifions si la liste est vide
        if (users.isEmpty()){
            return new ResponseEntity<>("UserListByUsernameIsEmpty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    // API Rest pour l'inscription d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody HashMap<String, String> request){
        String firstname = request.get("firstname");
        String lastname = request.get("lastname");
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");

        // Vérifions si le username n'existe pas déjà
        if (accountService.findByUsername(username) != null){
            return new ResponseEntity<>("UsernameAlreadyExist", HttpStatus.CONFLICT);
        }

        // Vérifons si l'email n'existe pas déjà
        if (accountService.findByEmail(email) != null){
            return new ResponseEntity<>("EmailAlreadyExist", HttpStatus.CONFLICT);
        }

        // Si le username et l'email n'existent pas, alors essayons d'enregistrer l'utilisateur
        try {
            AppUser user = accountService.saveUser(firstname, lastname, username, password,email);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>("ProblemeConnexionRessayer",HttpStatus.EXPECTATION_FAILED);
        }

    }



    // API Rest pour modifier l'utilisateur
    @PostMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody HashMap<String, String> request){
        String iduser = request.get("id");
        AppUser user = accountService.findUserById(Long.parseLong(iduser));
        if (user == null){
            return new ResponseEntity<>("userNotFound", HttpStatus.NOT_FOUND);
        }
        try {
            userImageId = user.getId();
            accountService.updateUser(user, request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("prfileNotUpdate", HttpStatus.EXPECTATION_FAILED);
        }
    }



    // API Rest pour changer la photo de profile
    @PostMapping("/photo/upload")
    public ResponseEntity<String> fileUpload(@RequestParam("image") MultipartFile multipartFile){
        try {
            accountService.saveUserImage(multipartFile, userImageId);
            return new ResponseEntity<>("photo enregister !", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("photoNotSave", HttpStatus.EXPECTATION_FAILED);
        }
    }


    // API Rest pour changer son mots de passe
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request){
        // vérifions l'utilisateur
        String username = request.get("username");
        AppUser appUser = accountService.findByUsername(username);
        if (appUser == null){
            return new ResponseEntity<>("UserNotFound", HttpStatus.NOT_FOUND);
        }

        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");
        String confirmPassword = request.get("confirmPassword");

        // Vérifions si le nouveau mots de passe correspond au mots de passe confirmation ou pas
        if (!newPassword.equals(confirmPassword)){
            return new ResponseEntity<>("passwordNotConform", HttpStatus.BAD_REQUEST);
        }

        // Récupérons actuel mots de passe de l'utilisatur
        String userPassword = appUser.getPassword();

        // Maintenant essayons de changer le mots de passe
        try {
            if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)){
                // Vérifions si le mots de passe actuel correspond à celui dans la base de donnée
                if (bCryptPasswordEncoder.matches(currentPassword, userPassword)){
                    accountService.updateUserPassword(appUser, newPassword);
                }else{
                    return new ResponseEntity<>("incorrectCurrentPassword", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>("PasswordChangedSuccefully", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Une erreu s'est produit", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success !", HttpStatus.OK);
    }

}
