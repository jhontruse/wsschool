package com.pe.intranet.wsschool.model.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Value;

//PASO 7 -JWT
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    OffsetDateTime timestamp;

    int status;

    String code; // Código estable para frontend y logs (ej.: AUTH_LOCKED, JWT_EXPIRED)

    String message; // Mensaje legible para el usuario

    String detail; // Clase/causa técnica opcional

    String path; // request.getRequestURI()

    String correlationId; // si usas MDC o header X-Correlation-Id

    List<String> errors; // lista de errores de validación, etc.

}
