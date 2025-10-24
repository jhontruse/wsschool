package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 3 -JWT
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un usuario en el sistema")
public class Usuario {

    @Schema(description = "ID único de usuario", example = "A87S6DA8D76AS8D68DA23")
    private UUID idUsuario;

    @Schema(description = "Usuario único de usuario", example = "JHONTRUSE")
    private String usuario;

    @Schema(description = "Email único de usuario", example = "jhontruse@gmail.com")
    private String email;

    @Schema(description = "Password único de usuario", example = "123")
    private String password;

    @Schema(description = "Estado único de usuario - 1 = activo, 0 = deshabilitado", example = "123")
    private Boolean activo; // 1 = activo, 0 = deshabilitado

    @Schema(description = "Registro único de usuario", example = "2025-10-24 15:33:36")
    private LocalDateTime createAt;

    @Schema(description = "Actualizacion único de usuario", example = "2025-10-24 15:33:36")
    private LocalDateTime updateAt;

    @Schema(description = "Bloqueo único de usuario - 1 = bloqueado, 0 = no", example = "123")
    private Boolean locked; // 1 = bloqueado, 0 = no

    @Schema(description = "Fecha de expiración de cuenta", example = "2025-10-24 15:33:36")
    private LocalDateTime accountExpiresAt; // fecha de expiración de cuenta

    @Schema(description = "Fecha de expiración de credenciales", example = "2025-10-24 15:33:36")
    private LocalDateTime credentialsExpiresAt; // fecha de expiración de credenciales

    @Schema(description = "Reintentos para el bloqueo", example = "5")
    private Integer failedLoginAttempts;// para políticas de bloqueo

    @Schema(description = "Fecha y hora en que se bloqueó la cuenta", example = "2025-10-24 15:33:36")
    private LocalDateTime lockedAt; // fecha y hora en que se bloqueó la cuenta

}
