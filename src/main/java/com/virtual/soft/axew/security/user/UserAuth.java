package com.virtual.soft.axew.security.user;

import com.virtual.soft.axew.entity.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserAuth implements UserDetails {

    private static final long serialVersionUID = 7334847120788745062L;
    private final Long id;
    private final String userName;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public UserAuth(Long id, String userName, String password, Set<RoleEnum> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public boolean hasRole(RoleEnum role) {
        return authorities.contains(new SimpleGrantedAuthority(role.getCode()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
