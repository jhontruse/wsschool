package com.pe.intranet.wsschool.exception;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pe.intranet.wsschool.model.dto.ApiError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

//PASO 11 -JWT
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalAuthExceptionHandler {

    private final HttpServletRequest request;

    private String cid() {
        return request.getHeader("X-Correlation-Id");
    }

    // ==== Autenticación (login) ====

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError badCredentials(BadCredentialsException ex) {
        return ApiErrorFactory.of(401, "AUTH_BAD_CREDENTIALS",
                "Usuario o contraseña incorrectos.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError locked(LockedException ex) {
        return ApiErrorFactory.of(401, "AUTH_LOCKED",
                "Tu cuenta está bloqueada. Contacta al administrador.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError disabled(DisabledException ex) {
        return ApiErrorFactory.of(401, "AUTH_DISABLED",
                "Tu cuenta está deshabilitada.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError credentialsExpired(CredentialsExpiredException ex) {
        return ApiErrorFactory.of(401, "AUTH_CREDENTIALS_EXPIRED",
                "Tu contraseña ha expirado. Debes actualizarla.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    @ExceptionHandler(AccountExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError accountExpired(AccountExpiredException ex) {
        return ApiErrorFactory.of(401, "AUTH_ACCOUNT_EXPIRED",
                "Tu cuenta ha expirado.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    // ==== Autorización (también puede saltar aquí si ocurre dentro del controller)
    // ====

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError accessDenied(AccessDeniedException ex) {
        return ApiErrorFactory.of(403, "AUTH_FORBIDDEN",
                "No tienes permisos para acceder a este recurso.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    // ==== JWT (filtros/servicios que validan token) ====

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError jwtExpired(ExpiredJwtException ex) {
        return ApiErrorFactory.of(401, "JWT_EXPIRED",
                "Tu token ha expirado.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError jwtInvalid(JwtException ex) {
        return ApiErrorFactory.of(401, "JWT_INVALID",
                "Token inválido.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

    // ==== Validación de request ====

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.toList());

        return ApiErrorFactory.of(400, "BAD_REQUEST",
                "Solicitud inválida. Revisa los campos.",
                ex.getClass().getSimpleName(), request, cid(), errors);
    }

    // ==== Fallback auth ====

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError authGeneric(AuthenticationException ex) {
        return ApiErrorFactory.of(401, "AUTH_ERROR",
                "Error de autenticación.",
                ex.getClass().getSimpleName(), request, cid(), null);
    }

}
