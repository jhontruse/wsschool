package com.pe.intranet.wsschool.exception;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import com.pe.intranet.wsschool.model.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

//PASO 10 -JWT
public final class ApiErrorFactory {

    private static final ZoneId LIMA = ZoneId.of("America/Lima");

    private ApiErrorFactory() {
    }

    public static ApiError of(int status, String code, String message,
            String detail, HttpServletRequest req, String correlationId,
            List<String> errors) {
        return ApiError.builder()
                .timestamp(OffsetDateTime.now(LIMA))
                .status(status)
                .code(code)
                .message(message)
                .detail(detail)
                .path(req != null ? req.getRequestURI() : null)
                .correlationId(correlationId)
                .errors(errors)
                .build();
    }

}
