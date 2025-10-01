package com.pe.intranet.wsschool.rowMapper;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.pe.intranet.wsschool.model.entity.Rol;
import com.pe.intranet.wsschool.util.ResultSetUtils;
import jakarta.annotation.Nullable;

//PASO 5 -JWT
@Component
public class RolRowMapper implements RowMapper<Rol> {

    @Override
    @Nullable
    public Rol mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
        return Rol.builder()
                .idRol(ResultSetUtils.uuid(rs, "ID_ROL"))
                .nombre(ResultSetUtils.str(rs, "NOMBRE"))
                .descripcion(ResultSetUtils.str(rs, "DESCRIPCION"))
                .activo(ResultSetUtils.bool(rs, "ACTIVO"))
                .createAt(ResultSetUtils.ldt(rs, "CREATE_AT"))
                .updateAt(ResultSetUtils.ldt(rs, "UPDATE_AT"))
                .build();
    }

    @Nullable
    public Rol mapRow(ResultSet rs, String preFix) throws java.sql.SQLException {
        return Rol.builder()
                .idRol(ResultSetUtils.uuid(rs, preFix + "ID_ROL"))
                .nombre(ResultSetUtils.str(rs, preFix + "NOMBRE"))
                .descripcion(ResultSetUtils.str(rs, preFix + "DESCRIPCION"))
                .activo(ResultSetUtils.bool(rs, preFix + "ACTIVO"))
                .createAt(ResultSetUtils.ldt(rs, preFix + "CREATE_AT"))
                .updateAt(ResultSetUtils.ldt(rs, preFix + "UPDATE_AT"))
                .build();
    }

}
