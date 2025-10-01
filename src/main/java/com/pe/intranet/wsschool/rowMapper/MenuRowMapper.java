
package com.pe.intranet.wsschool.rowMapper;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.pe.intranet.wsschool.model.entity.Menu;
import com.pe.intranet.wsschool.util.ResultSetUtils;
import jakarta.annotation.Nullable;

//PASO 4 -JWT
@Component
public class MenuRowMapper implements RowMapper<Menu> {

    @Override
    @Nullable
    public Menu mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
        return Menu.builder()
                .idMenu(ResultSetUtils.uuid(rs, "ID_MENU"))
                .nombre(ResultSetUtils.str(rs, "NOMBRE"))
                .path(ResultSetUtils.str(rs, "PATH"))
                .icono(ResultSetUtils.str(rs, "ICONO"))
                .orden(ResultSetUtils.i(rs, "ORDEN"))
                .idPadre(ResultSetUtils.uuid(rs, "ID_PADRE"))
                .activo(ResultSetUtils.bool(rs, "ACTIVO"))
                .createAt(ResultSetUtils.ldt(rs, "CREATE_AT"))
                .updateAt(ResultSetUtils.ldt(rs, "UPDATE_AT"))
                .build();
    }

    @Nullable
    public Menu mapRow(ResultSet rs, String preFix) throws java.sql.SQLException {
        return Menu.builder()
                .idMenu(ResultSetUtils.uuid(rs, preFix + "ID_MENU"))
                .nombre(ResultSetUtils.str(rs, preFix + "NOMBRE"))
                .path(ResultSetUtils.str(rs, preFix + "PATH"))
                .icono(ResultSetUtils.str(rs, preFix + "ICONO"))
                .orden(ResultSetUtils.i(rs, preFix + "ORDEN"))
                .idPadre(ResultSetUtils.uuid(rs, preFix + "ID_PADRE"))
                .activo(ResultSetUtils.bool(rs, preFix + "ACTIVO"))
                .createAt(ResultSetUtils.ldt(rs, preFix + "CREATE_AT"))
                .updateAt(ResultSetUtils.ldt(rs, preFix + "UPDATE_AT"))
                .build();
    }

}