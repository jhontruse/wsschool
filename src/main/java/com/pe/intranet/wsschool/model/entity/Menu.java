package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 1 -JWT
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private UUID idMenu;
    private String nombre;
    private String path;
    private String icono;
    private int orden;
    private UUID idPadre;
    private boolean activo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
