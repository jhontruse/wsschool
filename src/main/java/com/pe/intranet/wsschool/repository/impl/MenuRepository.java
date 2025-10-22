package com.pe.intranet.wsschool.repository.impl;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pe.intranet.wsschool.model.entity.Menu;
import com.pe.intranet.wsschool.repository.IMenuRepository;
import com.pe.intranet.wsschool.rowMapper.MenuRowMapper;
import lombok.RequiredArgsConstructor;

//PASO 17 -JWT
@Repository
@RequiredArgsConstructor
public class MenuRepository implements IMenuRepository {

    private final JdbcTemplate jdbc;

    private static final MenuRowMapper RMMenu = new MenuRowMapper();

    private static final String getRolByUsuarioSQL = " SELECT M.* FROM MENU M INNER JOIN ROL_MENU RM ON M.ID_MENU = RM.ID_MENU INNER JOIN ROL R ON R.ID_ROL = RM.ID_ROL INNER JOIN USUARIO_ROL UR ON UR.ID_ROL = R.ID_ROL INNER JOIN USUARIO U ON U.ID_USUARIO = UR.ID_USUARIO WHERE U.USUARIO = ? ORDER BY M.ORDEN ";

    private static final String getRolAllSQL = " SELECT * FROM MENU ORDER BY ORDEN";

    @Override
    public List<Menu> getMenuByUsuario(String usuario) {
        return jdbc.query(getRolByUsuarioSQL, RMMenu, usuario).stream().toList();
    }

    @Override
    public List<Menu> getMenuAll() {
        return jdbc.query(getRolAllSQL, RMMenu).stream().toList();
    }

}
