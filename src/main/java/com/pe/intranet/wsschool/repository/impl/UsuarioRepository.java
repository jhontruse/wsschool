package com.pe.intranet.wsschool.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pe.intranet.wsschool.model.entity.Usuario;
import com.pe.intranet.wsschool.repository.IUsuarioRepository;
import com.pe.intranet.wsschool.rowMapper.UsuarioRowMapper;

import lombok.RequiredArgsConstructor;

//PASO 19 -JWT
@Repository
@RequiredArgsConstructor
public class UsuarioRepository implements IUsuarioRepository {

    private final JdbcTemplate jdbc;

    private static final UsuarioRowMapper RMUsuario = new UsuarioRowMapper();

    private static final String getUsuarioByUsuarioSQL = " SELECT * FROM USUARIO WHERE USUARIO = ? ";

    private static final String getUsuarioAllSQL = " SELECT * FROM USUARIO ";

    private static final String getUsuarioByIdSQL = " SELECT * FROM USUARIO WHERE ID_USUARIO = ? ";

    private static final String incrementFailedAttemptsSQL = " UPDATE USUARIO SET FAILED_LOGIN_ATTEMPTS = FAILED_LOGIN_ATTEMPTS + 1, UPDATE_AT = CURRENT_TIMESTAMP WHERE USUARIO = ? ";

    private static final String lockUserSQL = " UPDATE USUARIO SET LOCKED = 1, LOCKED_AT = ?, UPDATE_AT = CURRENT_TIMESTAMP WHERE USUARIO = ? ";

    private static final String resetFailedAttemptsAndUnlockIfNeededSQL = " UPDATE USUARIO SET FAILED_LOGIN_ATTEMPTS = 0, LOCKED = 0, LOCKED_AT = NULL, UPDATE_AT = CURRENT_TIMESTAMP WHERE USUARIO = ? ";

    private static final String unlockIfLockExpiredSQL = " UPDATE USUARIO SET LOCKED = 0, LOCKED_AT = NULL, FAILED_LOGIN_ATTEMPTS = 0, UPDATE_AT = CURRENT_TIMESTAMP WHERE USUARIO = ? AND LOCKED = 1 AND LOCKED_AT IS NOT NULL AND TIMESTAMPADD(MINUTE, ?, LOCKED_AT) <= ?  ";

    @Override
    public Optional<Usuario> getUsuarioByUsuario(String usuario) {
        return jdbc.query(getUsuarioByUsuarioSQL, RMUsuario, usuario).stream().findFirst();
    }

    @Override
    public List<Usuario> getUsuarioAll() {
        return jdbc.query(getUsuarioAllSQL, RMUsuario).stream().toList();
    }

    @Override
    public Optional<Usuario> getUsuarioById(UUID id) {
        return jdbc.query(getUsuarioByIdSQL, RMUsuario, id).stream().findFirst();
    }

    @Transactional
    @Override
    public void incrementFailedAttempts(String usuario) {
        // incrementa y actualiza updated_at
        jdbc.update(incrementFailedAttemptsSQL, usuario);
    }

    @Transactional
    @Override
    public void lockUser(String usuario, LocalDateTime lockedAt) {
        jdbc.update(lockUserSQL, lockedAt, usuario);
    }

    @Transactional
    @Override
    public void resetFailedAttemptsAndUnlockIfNeeded(String username) {
        jdbc.update(resetFailedAttemptsAndUnlockIfNeededSQL, username);
    }

    /**
     * Desbloquea automáticamente si han pasado 'minutesThreshold' minutos desde
     * locked_at.
     * Devuelve true si desbloqueó; false si sigue bloqueado o no aplicaba.
     */
    @Transactional
    @Override
    public boolean unlockIfLockExpired(String username, LocalDateTime now, long minutesThreshold) {
        // Solo desbloquea si locked=1 y locked_at + threshold <= now
        int rowsAffected = jdbc.update(unlockIfLockExpiredSQL, username, minutesThreshold, now);
        return rowsAffected > 0;
    }

}
