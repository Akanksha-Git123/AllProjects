package com.shopappln.com.exception;


import com.shopappln.com.dto.AuthResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<AuthResponse> handleConstraint(DataIntegrityViolationException ex) {
return ResponseEntity.status(409).body(new AuthResponse(null, "Duplicate or constraint violation"));
}
}