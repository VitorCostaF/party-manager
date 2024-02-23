package br.com.ieoafestasedecoracoes.partymanager.filter.decoration;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.specification.DecorationSpecification;

@Component
public class DecorationProvinceFilter implements DecorationFilter {

	@Override
	public Specification<Decoration> applyFilter(Specification<Decoration> specification, Map<String, String> params) {
		
		if( params.get("provinceId") != null && !params.get("provinceId").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasProvince(Integer.valueOf(params.get("provinceId"))));
		}	
		return specification;
	}

}
