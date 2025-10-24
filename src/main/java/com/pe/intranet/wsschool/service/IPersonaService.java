package com.pe.intranet.wsschool.service;

import java.util.Optional;

import com.pe.intranet.wsschool.model.entity.Persona;

public interface IPersonaService {

  Optional<Persona> getPersonaByUserName(String userName);

}
