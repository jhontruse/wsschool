package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 1 -JWT
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un Menu en el sistema")
public class Menu {

    @Schema(description = "ID único de menu", example = "A87S6DA8D76AS8D68DA23")
    private UUID idMenu;

    @Schema(description = "Nombre único de menu", example = "HOME")
    private String nombre;

    @Schema(description = "Ruta único de menu", example = "/HOME")
    private String path;

    @Schema(description = "Icono único de menu", example = "HOME")
    private String icono;

    @Schema(description = "Orden único de menu", example = "1")
    private int orden;

    @Schema(description = "ID único de menu padre", example = "A87S6DA8D76AS8D68DA23")
    private UUID idPadre;

    @Schema(description = "Estado único de menu", example = "true")
    private boolean activo;

    @Schema(description = "Registro único de menu", example = "2025-10-24 15:33:36")
    private LocalDateTime createAt;

    @Schema(description = "Actualizacion único de menu", example = "2025-10-24 15:33:36")
    private LocalDateTime updateAt;

}
