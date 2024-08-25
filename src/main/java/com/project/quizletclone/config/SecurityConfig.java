package com.project.quizletclone.config;

import com.project.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomAuthenticationProvider customAuthenticationProvider;

//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Allow access to static resources
                        .requestMatchers("/Login_v4/**").permitAll()
                        .requestMatchers("/signup-form-19/**").permitAll()
                        // Add a matcher for sign-up page URL
                        .requestMatchers("/api/users/signup").permitAll()
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/users/redirectAfterLogin").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/api/users/login")
                        .successHandler(new CustomAuthenticationSuccessHandler()) // Set custom success handler
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .httpBasic((basic) -> basic
                        .realmName("QuizletClone")
                )
                .authenticationProvider(customAuthenticationProvider); // Ensure custom provider is used
        return http.build();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return customAuthenticationProvider;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            com.project.quizletclone.model.User user = userService.findUserByUsername(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//        };
//    }

}
