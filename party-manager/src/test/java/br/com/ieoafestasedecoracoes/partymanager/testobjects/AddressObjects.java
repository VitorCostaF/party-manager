package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.to.AddressTO;
import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
public class AddressObjects implements DomainObjectsToTest <AddressTO> {
	
	private final AddressTO objectById = new AddressTO(1, "By Id Street", "By Id City", "123456", "Complement By Id");
	private final AddressTO objectToDelete = new AddressTO(1, "Street To Delete", "City To Delete", "404", "Complement To Delete");
	private final AddressTO objectToUpdate = new AddressTO(1, "Street To Update", "City To Update", "200", "Complement To Update");
	private final AddressTO objectUpdated = new AddressTO(1, "Street Updated", "City Updated", "200", "Complement Updated");
	private final AddressTO objectToCreate = new AddressTO(1, "Street Address Created", "City Address Created", "1234", "Complement Address Created");
	
	private final String path = "/addresses";
	private final String pathId = path + "/{id}";
	private final String objectName = "Address";
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		return Collections.unmodifiableMap(new HashMap<>());
	}

}
