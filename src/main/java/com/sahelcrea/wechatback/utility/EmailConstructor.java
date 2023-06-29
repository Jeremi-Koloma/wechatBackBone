package com.sahelcrea.wechatback.Utility;

import com.sahelcrea.wechatback.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component // pour que la classe exécute
public class EmailConstructor {
    // pour avoir accès aux variable dans le properites
    @Autowired
    private Environment environment;


    // pour pouvoir construire une template html
    @Autowired
    private TemplateEngine templateEngine;


    // Méthode pour envoyé un mail à l'utilisateur quand il s'inscrit à l'appli
    public MimeMessagePreparator constructNewUserSigninEmail(AppUser user, String password){
        // Content de thymeleaf pour lier notre AppUser au variable context de thymeleaf
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        String text = templateEngine.process("signinUserTemplate",context);

        // Envoie de l'email
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail()); // envoie au mail de l'utilisateur
                email.setSubject("Welcome | Wechat");
                email.setText(text, true);
                email.setFrom(new InternetAddress(environment.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }




    // Méthode pour envoyé un mail à l'utilisateur quand il oublie son mots de passe
    public MimeMessagePreparator constructRessetPasswordEmail(AppUser user, String password){
        // Content de thymeleaf pour lier notre AppUser au variable context de thymeleaf
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        String text = templateEngine.process("ressetPasswordTemplate",context);

        // Envoie de l'email
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail()); // envoie au mail de l'utilisateur
                email.setSubject("RessetPassword | Wechat");
                email.setText(text, true);
                email.setFrom(new InternetAddress(environment.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }


}
