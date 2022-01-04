package com.smartcookie.service;

import com.smartcookie.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public CustomUserDetails() {
    }

    public CustomUserDetails(Long id, String firstName, String secondName, String email, String password, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static CustomUserDetails build(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.id = user.getId();
        customUserDetails.firstName = user.getFirstName();
        customUserDetails.secondName = user.getSecondName();
        customUserDetails.email = user.getEmail();
        customUserDetails.password = user.getPassword();
        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()   {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
