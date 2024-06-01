package com.contactmanager.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.contactmanager.scm.services.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService securityService;

    @Autowired
    private OAuthAuthenticationSuccessHandlender handler;

    //User create and login using in memory service
    // @Bean
    // public UserDetailsService userDetailsService()
    // {
    //     UserDetails userDetails = User.withUsername("tom")
    //                                 .password("tom")
    //                                 .roles("ADMIN")
    //                                 .build();

    //     UserDetails userDetails2 = User.wthUsername("payal")
    //                                 .password("payal")
    //                                 .roles("USER")
    //                                 .build();
    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(userDetails,userDetails2); 
    //     return inMemoryUserDetailsManager;
    // }

    //Configuration of authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //Object of user Detail service 
        authenticationProvider.setUserDetailsService(securityService);
        //Object of password encoder
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        //URL's are configured here 
        httpSecurity.authorizeHttpRequests(authRequest -> {
            authRequest.requestMatchers("/user/**").authenticated();
            authRequest.anyRequest().permitAll();
        });
        //to modify login functionality
        //this will give you spring boot default page
        //httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            //formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(formlogout -> {
            formlogout.logoutUrl("/logout");
            formlogout.logoutSuccessUrl("/login?logout=true");
        });

        //Oauth2 configuration
        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);

        });

        return httpSecurity.build();
    }
}
