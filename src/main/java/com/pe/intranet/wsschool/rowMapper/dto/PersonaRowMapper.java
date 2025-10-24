package com.pe.intranet.wsschool.rowMapper.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.pe.intranet.wsschool.model.entity.Menu;
import com.pe.intranet.wsschool.model.entity.Persona;
import com.pe.intranet.wsschool.util.ResultSetUtils;

@Component
public class PersonaRowMapper implements RowMapper<Persona> {

  @Override
  @Nullable
  public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Persona.builder()
        .idPersona(ResultSetUtils.uuid(rs, "ID_PERSONA"))
        .idUsuario(ResultSetUtils.uuid(rs, "ID_USUARIO"))
        .nombres(ResultSetUtils.str(rs, "NOMBRES"))
        .apePaterno(ResultSetUtils.str(rs, "APE_PATERNO"))
        .apeMaterno(ResultSetUtils.str(rs, "APE_MATERNO"))
        .sexo(ResultSetUtils.str(rs, "SEXO"))
        .dni(ResultSetUtils.str(rs, "DNI"))
        .celular(ResultSetUtils.str(rs, "CELULAR"))
        .direccion(ResultSetUtils.str(rs, "DIRECCION"))
        .createAt(ResultSetUtils.ldt(rs, "CREATE_AT"))
        .updateAt(ResultSetUtils.ldt(rs, "UPDATE_AT"))
        .build();
  }

  @Nullable
  public Persona mapRow(ResultSet rs, String preFix) throws SQLException {
    return Persona.builder()
        .idPersona(ResultSetUtils.uuid(rs, preFix + "ID_PERSONA"))
        .idUsuario(ResultSetUtils.uuid(rs, preFix + "ID_USUARIO"))
        .nombres(ResultSetUtils.str(rs, preFix + "NOMBRES"))
        .apePaterno(ResultSetUtils.str(rs, preFix + "APE_PATERNO"))
        .apeMaterno(ResultSetUtils.str(rs, preFix + "APE_MATERNO"))
        .sexo(ResultSetUtils.str(rs, preFix + "SEXO"))
        .dni(ResultSetUtils.str(rs, preFix + "DNI"))
        .celular(ResultSetUtils.str(rs, preFix + "CELULAR"))
        .direccion(ResultSetUtils.str(rs, preFix + "DIRECCION"))
        .createAt(ResultSetUtils.ldt(rs, preFix + "CREATE_AT"))
        .updateAt(ResultSetUtils.ldt(rs, preFix + "UPDATE_AT"))
        .build();
  }

}
