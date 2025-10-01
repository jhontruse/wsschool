package com.pe.intranet.wsschool.repository;

import java.util.List;

import com.pe.intranet.wsschool.model.entity.Rol;

//PASO 15 -JWT
public interface IRolRepository {

    List<Rol> getRolByUsuario(String usuario);

    List<Rol> getRolAll();

}
