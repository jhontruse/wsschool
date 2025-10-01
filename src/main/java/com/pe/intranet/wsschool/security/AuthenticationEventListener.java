package com.pe.intranet.wsschool.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.pe.intranet.wsschool.model.entity.Usuario;
import com.pe.intranet.wsschool.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationEventListener {

    private final IUsuarioRepository iUsuarioRepository;

    // private static final int MAX_ATTEMPTS = 5;
    @Value("${security.jwt.max-attempts}")
    private String MAX_ATTEMPTS;

    // private static final long TEMP_LOCK_MINUTES = 30; // si usas bloqueo temporal
    @Value("${security.jwt.temp-lock-minutes}")
    private String TEMP_LOCK_MINUTES;

    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
        String username = String.valueOf(event.getAuthentication().getPrincipal());

        // si tu política incluye bloqueo temporal por expiración del lock, puedes
        // revisar/desbloquear antes
        iUsuarioRepository.unlockIfLockExpired(username, LocalDateTime.now(ZoneId.of("America/Lima")),
                Integer.parseInt(TEMP_LOCK_MINUTES));

        iUsuarioRepository.incrementFailedAttempts(username);

        Optional<Usuario> usuario = iUsuarioRepository.getUsuarioByUsuario(username);
        if (usuario.isPresent() && usuario.get().getFailedLoginAttempts() >= Long.parseLong(MAX_ATTEMPTS)) {
            iUsuarioRepository.lockUser(username, LocalDateTime.now(ZoneId.of("America/Lima")));
        }
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        if (username == null || username.isBlank())
            return;
        iUsuarioRepository.resetFailedAttemptsAndUnlockIfNeeded(username);
    }

}
