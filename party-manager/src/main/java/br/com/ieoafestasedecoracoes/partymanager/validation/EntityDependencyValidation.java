package br.com.ieoafestasedecoracoes.partymanager.validation;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.DomainObjetctInterface;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityDependencyValidation {

	public static <T extends DomainObjetctInterface> T validate(JpaRepository<T, Integer> repository, Integer id,
			String weakEntityName, String strongEntityName) {
		T entity = repository.findById(id).orElse(null);
		if (entity == null) {
			throw new RuntimeException(weakEntityName + " with id " + id + " not found for " + strongEntityName);
		}
		return entity;
	}

}
