
package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 2 -JWT
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    private UUID idRol;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
