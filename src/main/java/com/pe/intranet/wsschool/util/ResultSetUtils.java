package com.pe.intranet.wsschool.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

//PASO 14 -JWT
public class ResultSetUtils {

    private ResultSetUtils() {
    }

    public static String str(ResultSet rs, String col) throws SQLException {
        String v = rs.getString(col);
        return (rs.wasNull() ? null : v);
    }

    public static Integer i(ResultSet rs, String col) throws SQLException {
        int v = rs.getInt(col);
        return (rs.wasNull() ? null : v);
        // para primitivos int usa rs.getInt directamente
    }

    public static boolean bool(ResultSet rs, String col) throws SQLException {
        boolean v = rs.getBoolean(col);
        // si necesitas Boolean, crea otro método que devuelva null si wasNull()
        return v;
    }

    public static BigDecimal dec(ResultSet rs, String col) throws SQLException {
        BigDecimal v = rs.getBigDecimal(col);
        return (rs.wasNull() ? null : v);
    }

    public static LocalDateTime ldt(ResultSet rs, String col) throws SQLException {
        Timestamp ts = rs.getTimestamp(col);
        return (ts == null ? null : ts.toLocalDateTime());
    }

    public static Timestamp tms(ResultSet rs, String col) throws SQLException {
        Timestamp ts = rs.getTimestamp(col);
        return (ts == null ? null : ts);
    }

    public static LocalDate ld(ResultSet rs, String col) throws SQLException {
        Date d = rs.getDate(col);
        return d != null ? d.toLocalDate() : null;
    }

    public static LocalTime lt(ResultSet rs, String col) throws SQLException {
        Time t = rs.getTime(col);
        return (t == null ? null : t.toLocalTime());
    }

    public static UUID uuid(ResultSet rs, String col) throws SQLException {
        String val = rs.getString(col);
        if (val == null || val.isBlank()) {
            return null; // la columna estaba NULL o vacía
        }
        try {
            return UUID.fromString(val.trim());
        } catch (IllegalArgumentException e) {
            throw new SQLException("Valor inválido en columna " + col + ": [" + val + "] no es un UUID válido", e);
        }
    }

}
