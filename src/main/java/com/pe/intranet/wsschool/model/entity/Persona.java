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

  @Schema(description = "ID único de usuario", example = "A87S6DA8D76AS8D68DA23")
  private UUID idUsuario;

  @Schema(description = "Nombre único de persona", example = "Jhon")
  private String nombres;

  @Schema(description = "Apellido Paterno único de persona", example = "Trujillo")
  private String apePaterno;

  @Schema(description = "Apellido Materno único de persona", example = "Serrano")
  private String apeMaterno;

  @Schema(description = "Sexo único de persona", example = "M")
  private String sexo;

  @Schema(description = "DNI único de persona", example = "70607371")
  private String dni;

  @Schema(description = "Celular único de persona", example = "953979945")
  private String celular;

  @Schema(description = "Dirección único de persona", example = "Jr. Boccioni 460")
  private String direccion;

  @Schema(description = "Registro único de persona", example = "2025-10-24 15:33:36")
  private LocalDateTime createAt;

  @Schema(description = "Actualizacion único de persona", example = "2025-10-24 15:33:36")
  private LocalDateTime updateAt;

}
