package com.pe.intranet.wsschool.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.intranet.wsschool.model.entity.Rol;
import com.pe.intranet.wsschool.repository.IRolRepository;
import com.pe.intranet.wsschool.rowMapper.RolRowMapper;

import lombok.RequiredArgsConstructor;

//PASO 18 -JWT
@Repository
@RequiredArgsConstructor
public class RolRepository implements IRolRepository {

    private final JdbcTemplate jdbc;

    private static final RolRowMapper RMRol = new RolRowMapper();

    private static final String getRolByUsuarioSQL = " SELECT R.* FROM ROL R INNER JOIN USUARIO_ROL UR ON UR.ID_ROL=R.ID_ROL INNER JOIN USUARIO U ON U.ID_USUARIO=UR.ID_USUARIO WHERE U.USUARIO = ? ";

    private static final String getRolAllSQL = " SELECT * FROM ROL ";

    @Override
    public List<Rol> getRolByUsuario(String usuario) {
        return jdbc.query(getRolByUsuarioSQL, RMRol, usuario).stream().toList();
    }

    @Override
    public List<Rol> getRolAll() {
        return jdbc.query(getRolAllSQL, RMRol).stream().toList();
    }

}
