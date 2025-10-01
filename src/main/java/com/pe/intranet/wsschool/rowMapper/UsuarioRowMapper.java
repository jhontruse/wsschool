package com.pe.intranet.wsschool.rowMapper;

import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.pe.intranet.wsschool.model.entity.Usuario;
import com.pe.intranet.wsschool.util.ResultSetUtils;
import jakarta.annotation.Nullable;

//PASO 6 -JWT
@Component
public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    @Nullable
    public Usuario mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
        return Usuario.builder()
                .idUsuario(ResultSetUtils.uuid(rs, "ID_USUARIO"))
                .usuario(ResultSetUtils.str(rs, "USUARIO"))
                .email(ResultSetUtils.str(rs, "EMAIL"))
                .password(ResultSetUtils.str(rs, "PASSWORD"))
                .activo(ResultSetUtils.bool(rs, "ACTIVO"))
                .createAt(ResultSetUtils.ldt(rs, "CREATE_AT"))
                .updateAt(ResultSetUtils.ldt(rs, "UPDATE_AT"))
                .locked(ResultSetUtils.bool(rs, "LOCKED"))
                .accountExpiresAt(ResultSetUtils.ldt(rs, "ACCOUNT_EXPIRES_AT"))
                .credentialsExpiresAt(ResultSetUtils.ldt(rs, "CREDENTIALS_EXPIRES_AT"))
                .failedLoginAttempts(ResultSetUtils.i(rs, "FAILED_LOGIN_ATTEMPTS"))
                .lockedAt(ResultSetUtils.ldt(rs, "LOCKED_AT"))
                .build();
    }

    @Nullable
    public Usuario mapRow(ResultSet rs, String preFix) throws java.sql.SQLException {
        return Usuario.builder()
                .idUsuario(ResultSetUtils.uuid(rs, preFix + "ID_USUARIO"))
                .usuario(ResultSetUtils.str(rs, preFix + "USUARIO"))
                .email(ResultSetUtils.str(rs, preFix + "EMAIL"))
                .password(ResultSetUtils.str(rs, preFix + "PASSWORD"))
                .activo(ResultSetUtils.bool(rs, preFix + "ACTIVO"))
                .createAt(ResultSetUtils.ldt(rs, preFix + "CREATE_AT"))
                .updateAt(ResultSetUtils.ldt(rs, preFix + "UPDATE_AT"))
                .locked(ResultSetUtils.bool(rs, preFix + "LOCKED"))
                .accountExpiresAt(ResultSetUtils.ldt(rs, preFix + "ACCOUNT_EXPIRES_AT"))
                .credentialsExpiresAt(ResultSetUtils.ldt(rs, preFix + "CREDENTIALS_EXPIRES_AT"))
                .failedLoginAttempts(ResultSetUtils.i(rs, preFix + "FAILED_LOGIN_ATTEMPTS"))
                .lockedAt(ResultSetUtils.ldt(rs, preFix + "LOCKED_AT"))
                .build();
    }

}
