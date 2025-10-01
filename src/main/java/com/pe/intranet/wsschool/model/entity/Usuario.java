package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 3 -JWT
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private UUID idUsuario;
    private String usuario;
    private String email;
    private String password;
    private Boolean activo; // 1 = activo, 0 = deshabilitado
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean locked; // 1 = bloqueado, 0 = no
    private LocalDateTime accountExpiresAt; // fecha de expiración de cuenta
    private LocalDateTime credentialsExpiresAt; // fecha de expiración de credenciales
    private Integer failedLoginAttempts;// para políticas de bloqueo
    private LocalDateTime lockedAt; // fecha y hora en que se bloqueó la cuenta

}