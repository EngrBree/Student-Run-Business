package com.bree.usermanage.Security.Config;

import com.bree.usermanage.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig  {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

 @Bean
   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
               .csrf().disable()
               .authorizeHttpRequests(authorize-> authorize
                       .requestMatchers("users/register")
                       .permitAll()
                       .anyRequest()
                       .authenticated()
               ).formLogin();
        return httpSecurity.build();
   }


    @Bean
    public WebSecurityCustomizer securityCustomizer() throws Exception{
     return (web) -> web.ignoring().requestMatchers("users/register");

    }
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
