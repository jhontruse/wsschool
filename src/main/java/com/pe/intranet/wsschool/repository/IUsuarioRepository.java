package com.pe.intranet.wsschool.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pe.intranet.wsschool.model.entity.Usuario;

//PASO 16 -JWT
public interface IUsuarioRepository {

    Optional<Usuario> getUsuarioByUsuario(String usuario);

    List<Usuario> getUsuarioAll();

    Optional<Usuario> getUsuarioById(UUID id);

    void incrementFailedAttempts(String usuario);

    void lockUser(String usuario, LocalDateTime lockedAt);

    /**
     * Resetea intentos fallidos y desbloquea si estaba bloqueado.
     * Úsalo en AuthenticationSuccessEvent.
     */
    void resetFailedAttemptsAndUnlockIfNeeded(String usuario);

    /**
     * (Opcional) Desbloqueo por expiración del bloqueo temporal.
     * Si el bloqueo ya excedió el umbral, desbloquea y limpia intentos.
     */
    boolean unlockIfLockExpired(String usuario, LocalDateTime now, long minutesThreshold);

}
