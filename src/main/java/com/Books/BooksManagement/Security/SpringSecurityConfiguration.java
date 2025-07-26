package com.Books.BooksManagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.web.servlet.function.RequestPredicates.headers;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        Function<String,String> passwordEncoder= input->passwordEncoder().encode(input);
        UserDetails userDetails= User.builder().passwordEncoder(passwordEncoder)
                .username("Harshith").password("hkb").roles("USER","ADMIN").build();

        return new InMemoryUserDetailsManager(userDetails);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated());
        http.formLogin(withDefaults()); // Enable form login
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers
                .frameOptions(frame -> frame.disable()));

        return http.build();
    }

}
