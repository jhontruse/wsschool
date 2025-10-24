package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una persona en el sistema")
public class Persona {

  @Schema(description = "ID único de la persona", example = "A87S6DA8D76AS8D68DA87")
  private UUID idPersona;
  private UUID idUsuario;
  private String nombres;
  private String apePaterno;
  private String apeMaterno;
  private String sexo;
  private String dni;
  private String celular;
  private String direccion;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;

}
