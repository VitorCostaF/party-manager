package br.com.ieoafestasedecoracoes.partymanager.filter.decoration;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;

public interface DecorationFilter {

	Specification<Decoration> applyFilter(Specification<Decoration> specification, Map<String,String> params);
	
}
