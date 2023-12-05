package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
public class CompanyObjects implements DomainObjectsToTest<CompanyTO> {
	
	private final CompanyTO objectById = new CompanyTO(1, "Company By id", "1234");
	private final CompanyTO objectToDelete = new CompanyTO(1, "Company To Delete", "12345");
	private final CompanyTO objectToUpdate = new CompanyTO(1, "Company To Update", "123456");
	private final CompanyTO objectUpdated = new CompanyTO(1, "Company Updated", "12");
	private final CompanyTO objectToCreate = new CompanyTO(1, "Company To Create", "1234567");
	
	private String path = "/companies";
	private String pathId = path + "/{id}";
	private String objectName = "Company";
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		return Collections.unmodifiableMap(new HashMap<>());
	}
	
}
