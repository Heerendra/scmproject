package com.contactmanager.scm.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.contactmanager.scm.Repositories.UserRepo;
import com.contactmanager.scm.entities.Provider;
import com.contactmanager.scm.entities.User;
import com.contactmanager.scm.helpers.AppContants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class OAuthAuthenticationSuccessHandlender implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandlender.class);

    @Autowired
    private UserRepo user;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        logger.info("OAuthAuthenticationSuccessHandlender");

        //data saving in database 
        DefaultOAuth2User gUser = (DefaultOAuth2User)authentication.getPrincipal();
        gUser.getAttributes().forEach((key,value)->{
            logger.info("{} : {}", key, value);
        });

        User usr = new User();
        usr.setEmail(gUser.getAttribute("email").toString());
        usr.setFirstName(gUser.getAttribute("name").toString());
        usr.setProfilePic(gUser.getAttribute("picture").toString());
        usr.setPassword("password");
        usr.setUserId(UUID.randomUUID().toString());
        usr.setProvider(Provider.GOOGLE);
        usr.setEmailVerified(true);
        usr.setProviderUserId(gUser.getName());
        usr.setRoleList(List.of(AppContants.ROLE_USER));
        usr.setAbout("This user is created using Google authenticator");

        User dbuser = user.findByEmail(gUser.getAttribute("email").toString()).orElse(null);
        if (dbuser == null)
        {
            user.save(usr);
        }
        logger.info("User Saved");

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
        
    }

}
