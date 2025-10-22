package com.pe.intranet.wsschool.exception.type;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.intranet.wsschool.exception.ApiErrorFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//PASO 13 -JWT
@Component
@RequiredArgsConstructor
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException ex) {
        try {
            var err = ApiErrorFactory.of(
                    HttpStatus.FORBIDDEN.value(),
                    "AUTH_FORBIDDEN",
                    "No tienes permisos para acceder a este recurso.",
                    ex.getClass().getSimpleName(),
                    request,
                    request.getHeader("X-Correlation-Id"),
                    null);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), err);
        } catch (Exception ignored) {
        }
    }
}
