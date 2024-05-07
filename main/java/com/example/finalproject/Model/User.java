package com.example.finalproject.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer Id ;

    private String username ;

    private String password ;


    private String role ;


//    relations

    @OneToOne
    @JsonIgnore
    private Fan fan ;


    @OneToOne
    @JsonIgnore
    private SportAdmin sportAdmin ;


    @OneToOne
    @JsonIgnore
    private TeamAdmin teamAdmin ;


    @OneToOne
    @JsonIgnore
    private Athlete athlete ;


//    security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return " ";
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
