package br.com.ieoafestasedecoracoes.partymanager.filter.decoration;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.specification.DecorationSpecification;

@Component
public class DecorationAdvantageFilter implements DecorationFilter {

	@Override
	public Specification<Decoration> applyFilter(Specification<Decoration> specification, Map<String, String> params) {
		
		if( params.get("advantages") != null && !params.get("advantages").isEmpty()) {
			List<Integer> ids = Stream.of(params.get("advantages").split(",")).map(Integer::valueOf).toList();
			specification = specification.and(DecorationSpecification.hasAdvantages(ids));
		}	
		return specification;
	}

}
