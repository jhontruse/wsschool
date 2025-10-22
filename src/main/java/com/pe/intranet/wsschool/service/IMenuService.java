package com.pe.intranet.wsschool.service;

import java.util.List;
import com.pe.intranet.wsschool.model.entity.Menu;

public interface IMenuService {

  public List<Menu> getMenuByUsuario(String usuario);

}
