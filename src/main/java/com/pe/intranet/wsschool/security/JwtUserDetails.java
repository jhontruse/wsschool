package com.pe.intranet.wsschool.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pe.intranet.wsschool.model.entity.Rol;
import com.pe.intranet.wsschool.model.entity.Usuario;

import lombok.AllArgsConstructor;

//Clase S4.2
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {

    @Autowired
    private final Usuario user;

    @Autowired
    private final List<Rol> rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            List<GrantedAuthority> roles = new ArrayList<>();
            List<Rol> roleNames = this.rol;
            roleNames.forEach(roleName -> {
                roles.add(new SimpleGrantedAuthority("ROLE_" + roleName.getNombre()));
            });
            return roles;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los roles del usuario: " + user.getUsuario(), e);
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Lima"));
        boolean accountNonExpired = user.getAccountExpiresAt() == null
                || now.isBefore(user.getAccountExpiresAt());
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getLocked() == null || !user.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Lima"));
        boolean credentialsNonExpired = user.getCredentialsExpiresAt() == null
                || now.isBefore(user.getCredentialsExpiresAt());
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return user.getActivo();
    }

}
