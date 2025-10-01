package com.pe.intranet.wsschool.repository;

import java.util.List;

import com.pe.intranet.wsschool.model.entity.Menu;

//PASO 14 -JWT
public interface IMenuRepository {

    List<Menu> getMenuByUsuario(String usuario);

    List<Menu> getMenuAll();

}
