package br.com.ieoafestasedecoracoes.partymanager.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DomainErrorsValidation>> handlerError400(MethodArgumentNotValidException ex) {
		List<FieldError> errors = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(errors.stream().map(DomainErrorsValidation::new).toList());
	}
	
}
