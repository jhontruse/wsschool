package com.pe.intranet.wsschool.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.pe.intranet.wsschool.model.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final HttpServletRequest request;

  private String cid() {
    return request.getHeader("X-Correlation-Id");
  }

  @ExceptionHandler(DuplicateKeyException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ApiError duplicateKeyApiError(DuplicateKeyException ex) {
    return ApiErrorFactory.of(409, "DB_DUPLICATE_KEY",
        "El registro ya existe.",
        ex.getClass().getSimpleName(), request, cid(), null);
  }

  @ExceptionHandler(BadSqlGrammarException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError badSqlGrammar(BadSqlGrammarException ex) {
    return ApiErrorFactory.of(500, "DB_SQL_ERROR",
        "Error en la sentencia SQL.",
        ex.getClass().getSimpleName(), request, cid(), null);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ApiError dataIntegriViolation(DataIntegrityViolationException ex) {
    return ApiErrorFactory.of(422, "DB_INTEGRITY_VIOLATION",
        "Violación de integridad de datos.",
        ex.getClass().getSimpleName(), request, cid(), null);
  }

  @ExceptionHandler(QueryTimeoutException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ApiError queryTimeOut(QueryTimeoutException ex) {
    return ApiErrorFactory.of(503, "DB_TIMEOUT",
        "La base de datos no respondió a tiempo.",
        ex.getClass().getSimpleName(), request, cid(), null);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError exception(Exception ex) {
    return ApiErrorFactory.of(500, "DB_ERROR",
        "Error de acceso a datos.",
        ex.getClass().getSimpleName(), request, cid(), null);
  }

}
