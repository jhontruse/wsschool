
package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 2 -JWT
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un Rol en el sistema")
public class Rol {

    @Schema(description = "ID único de rol", example = "A87S6DA8D76AS8D68DA23")
    private UUID idRol;

    @Schema(description = "Nombre único de rol", example = "ALUMNO")
    private String nombre;

    @Schema(description = "Descripcion único de rol", example = "ALUMNO DEL SISTEMA")
    private String descripcion;

    @Schema(description = "Estado único de rol", example = "true")
    private boolean activo;

    @Schema(description = "Registro único de rol", example = "2025-10-24 15:33:36")
    private LocalDateTime createAt;

    @Schema(description = "Actualizacion único de rol", example = "2025-10-24 15:33:36")
    private LocalDateTime updateAt;

}
