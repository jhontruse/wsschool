package com.pe.intranet.wsschool.repository;

import java.util.Optional;
import com.pe.intranet.wsschool.model.entity.Persona;

public interface IPersonaRepository {

  Optional<Persona> getPersonaByUserName(String userName);

}
