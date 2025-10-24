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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/intranet/api/persona")
@Tag(name = "Persona", description = "Gestión de personas del sistema escolar")
@SecurityRequirement(name = "bearerAuth")
public class PersonaController {

  @Autowired
  private final IPersonaService iPersonaService;

  @Operation(summary = "Buscar personas por username", description = "Busca personas que coincidan con el username proporcionado")
  @PreAuthorize("@authorizeLogic.hasAccess('findPersonaByUsername')")
  @GetMapping("find/{username}")
  public ResponseEntity<Optional<Persona>> findPersonaByUsername(
      @Parameter(description = "username a buscar", example = "JHONTRUSE", required = true) @PathVariable("username") String username) {
    Optional<Persona> persona = iPersonaService.getPersonaByUserName(username);
    return ResponseEntity.ok(persona);
  }

}
