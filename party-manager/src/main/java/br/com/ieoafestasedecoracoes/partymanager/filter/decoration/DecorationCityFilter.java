package br.com.ieoafestasedecoracoes.partymanager.filter.decoration;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.specification.DecorationSpecification;

@Component
public class DecorationCityFilter implements DecorationFilter {

	@Override
	public Specification<Decoration> applyFilter(Specification<Decoration> specification, Map<String, String> params) {
		
		if( params.get("cityId") != null && !params.get("cityId").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasCity(Integer.valueOf(params.get("cityId"))));
		}	
		return specification;
	}

}
