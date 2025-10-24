package com.pe.intranet.wsschool.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.intranet.wsschool.model.entity.Persona;
import com.pe.intranet.wsschool.service.IPersonaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/intranet/api/persona")
public class PersonaController {

  @Autowired
  private final IPersonaService iPersonaService;

  @PreAuthorize("@authorizeLogic.hasAccess('findPersonaByUsername')")
  @GetMapping("find/{username}")
  public ResponseEntity<Optional<Persona>> findPersonaByUsername(@PathVariable("username") String username) {
    Optional<Persona> persona = iPersonaService.getPersonaByUserName(username);
    return ResponseEntity.ok(persona);
  }

}
