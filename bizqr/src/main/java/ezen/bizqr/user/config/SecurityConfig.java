package ezen.bizqr.user.config;

import ezen.bizqr.user.security.CustomUserService;
import ezen.bizqr.user.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/checkEmail/**","/asset/**","/image/**","/js/**","/smarteditor/**","/css/**","/index","/","/user/register","/user/login","/upload/**", "/filter").permitAll()
                        .requestMatchers("/user/list").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())

                .formLogin(login -> login
                        .usernameParameter("email")
                        .passwordParameter("pwd")
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/").permitAll())

                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/user/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)))

                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/"));



        return http.build();

    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws  Exception{
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserService();
    }


}

