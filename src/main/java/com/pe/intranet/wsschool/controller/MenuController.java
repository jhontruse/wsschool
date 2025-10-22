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
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/intranet/api/menu")
public class MenuController {

  @Autowired
  private final IMenuService iMenuService;

  @PreAuthorize("@authorizeLogic.hasAccess('findMenuByUsername')")
  @GetMapping("find/{username}")
  public ResponseEntity<List<Menu>> findMenuByUsername(@PathVariable("username") String username) {
    List<Menu> menus = iMenuService.getMenuByUsuario(username);
    return ResponseEntity.ok(menus);
  }

}
