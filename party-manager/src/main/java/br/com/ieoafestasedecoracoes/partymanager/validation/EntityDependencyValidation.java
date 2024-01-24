package br.com.ieoafestasedecoracoes.partymanager.validation;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.DomainObjetctInterface;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityDependencyValidation {

	public static void validate(JpaRepository<? extends DomainObjetctInterface, Integer> repository, Integer id,
			String weakEntityName, String strongEntityName) {
		if (!repository.existsById(id)) {
			throw new RuntimeException(weakEntityName + " with id " + id + " not found for " + strongEntityName);
		}
	}

}
