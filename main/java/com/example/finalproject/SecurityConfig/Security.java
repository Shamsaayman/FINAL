package com.example.finalproject.SecurityConfig;


import com.example.finalproject.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()


                .requestMatchers("api/v1/fan/add").permitAll()
                .requestMatchers("api/v1/athlete/register-athlete" ).permitAll()
                .requestMatchers("api/v1/sport-admin/register").permitAll()
                .requestMatchers("api/v1/team-admin/register").permitAll()
                .requestMatchers("api/v1/sport-admin/update").hasAuthority("SPORT-ADMIN")
                .requestMatchers("api/v1/team-admin/update").hasAuthority("TEAM-ADMIN")
                .requestMatchers("api/v1/athlete/update").hasAuthority("ATHLETE")
                .requestMatchers("api/v1/fan/update").hasAuthority("FAN")
//                .requestMatchers("/api/v1/auth/update").hasAnyAuthority("CUSTOMER")
//                .requestMatchers("/api/v1/auth/get","/api/v1/auth/delete" ).hasAnyAuthority("ADMIN")
//
//                .requestMatchers("/api/v1/sport-admin/add").permitAll()

                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}