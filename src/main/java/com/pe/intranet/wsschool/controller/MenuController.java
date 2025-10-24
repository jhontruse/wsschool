package com.pe.intranet.wsschool.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pe.intranet.wsschool.model.entity.Menu;
import com.pe.intranet.wsschool.service.IMenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/intranet/api/menu")
@Tag(name = "Menu", description = "Gestión de menu del sistema escolar")
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

  @Autowired
  private final IMenuService iMenuService;

  @Operation(summary = "Buscar menu por username", description = "Busca personas que coincidan con el username proporcionado")
  @PreAuthorize("@authorizeLogic.hasAccess('findMenuByUsername')")
  @GetMapping("find/{username}")
  public ResponseEntity<List<Menu>> findMenuByUsername(
      @Parameter(description = "username a buscar", example = "JHONTRUSE", required = true) @PathVariable("username") String username) {
    List<Menu> menus = iMenuService.getMenuByUsuario(username);
    return ResponseEntity.ok(menus);
  }

}
