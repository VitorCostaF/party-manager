package br.com.ieoafestasedecoracoes.partymanager.validation;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.DomainObjetctInterface;

public interface DomainValidation {

	static void validate(JpaRepository<? extends DomainObjetctInterface ,Integer> repository, Integer id, String weakEntityName, String strongEntityName) {}
	
}
