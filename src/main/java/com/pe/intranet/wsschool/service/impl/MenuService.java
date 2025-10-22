package com.pe.intranet.wsschool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.intranet.wsschool.model.entity.Menu;
import com.pe.intranet.wsschool.repository.IMenuRepository;
import com.pe.intranet.wsschool.service.IMenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {

  @Autowired
  private final IMenuRepository iMenuRepository;

  @Override
  public List<Menu> getMenuByUsuario(String usuario) {
    return iMenuRepository.getMenuByUsuario(usuario);
  }

}
