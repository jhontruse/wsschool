package com.pe.intranet.wsschool.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

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
