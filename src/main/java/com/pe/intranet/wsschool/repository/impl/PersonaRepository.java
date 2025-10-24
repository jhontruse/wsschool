package com.pe.intranet.wsschool.repository.impl;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.intranet.wsschool.model.entity.Persona;
import com.pe.intranet.wsschool.repository.IPersonaRepository;
import com.pe.intranet.wsschool.rowMapper.dto.PersonaRowMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonaRepository implements IPersonaRepository {

  private final JdbcTemplate jdbc;

  private static final PersonaRowMapper RMPersona = new PersonaRowMapper();

  private static final String getPersonaByUserNameSQL = " SELECT P.* FROM PERSONA P INNER JOIN USUARIO U ON U.ID_USUARIO=U.ID_USUARIO WHERE U.USUARIO=? ";

  @Override
  public Optional<Persona> getPersonaByUserName(String userName) {
    return jdbc.query(getPersonaByUserNameSQL, RMPersona, userName).stream().findFirst();
  }

}
