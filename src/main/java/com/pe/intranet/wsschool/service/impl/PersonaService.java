package com.pe.intranet.wsschool.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.intranet.wsschool.model.entity.Persona;
import com.pe.intranet.wsschool.repository.IPersonaRepository;
import com.pe.intranet.wsschool.service.IPersonaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService implements IPersonaService {

  @Autowired
  private final IPersonaRepository iPersonaRepository;

  @Override
  public Optional<Persona> getPersonaByUserName(String userName) {
    return iPersonaRepository.getPersonaByUserName(userName);
  }

}
