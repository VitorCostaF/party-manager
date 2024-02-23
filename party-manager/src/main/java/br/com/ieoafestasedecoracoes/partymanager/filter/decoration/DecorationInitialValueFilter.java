package br.com.ieoafestasedecoracoes.partymanager.filter.decoration;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.specification.DecorationSpecification;

@Component
public class DecorationInitialValueFilter implements DecorationFilter {

	@Override
	public Specification<Decoration> applyFilter(Specification<Decoration> specification, Map<String, String> params) {
		
		if( params.get("initialValue") != null && !params.get("initialValue").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasInitialValue(new BigDecimal(params.get("initialValue"))));
		}
		
		return specification;
	}

}
