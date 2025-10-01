package com.pe.intranet.wsschool.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pe.intranet.wsschool.model.entity.Rol;
import com.pe.intranet.wsschool.model.entity.Usuario;
import com.pe.intranet.wsschool.repository.IRolRepository;
import com.pe.intranet.wsschool.repository.IUsuarioRepository;
import com.pe.intranet.wsschool.security.JwtUserDetails;

import lombok.RequiredArgsConstructor;

//PASO 14 -JWT
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private static final long TEMP_LOCK_MINUTES = 30; // si usas bloqueo temporal

    @Autowired
    private final IUsuarioRepository iUsuarioRepository;

    @Autowired
    private final IRolRepository iRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = iUsuarioRepository.getUsuarioByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (usuario.getLocked() != null && usuario.getLocked() && usuario.getLockedAt() != null) {
            iUsuarioRepository.unlockIfLockExpired(username, LocalDateTime.now(ZoneId.of("America/Lima")),
                    TEMP_LOCK_MINUTES);
        }

        List<Rol> roles = iRolRepository.getRolByUsuario(username);

        return new JwtUserDetails(usuario, roles);
    }

}