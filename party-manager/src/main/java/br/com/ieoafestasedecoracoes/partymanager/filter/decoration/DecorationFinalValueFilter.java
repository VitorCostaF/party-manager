package br.com.ieoafestasedecoracoes.partymanager.filter.decoration;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.specification.DecorationSpecification;

@Component
public class DecorationFinalValueFilter implements DecorationFilter {

	@Override
	public Specification<Decoration> applyFilter(Specification<Decoration> specification, Map<String, String> params) {
		
		if( params.get("finalValue") != null && !params.get("finalValue").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasFinalValue(new BigDecimal(params.get("finalValue"))));
		}
		
		return specification;
	}

}
