package br.com.ieoafestasedecoracoes.partymanager.infra;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DomainErrorsValidation {

	private String field;
	private String message;

	public DomainErrorsValidation(FieldError error) {
		field = error.getField();
		message = error.getDefaultMessage();
	}
	
}
