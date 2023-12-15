package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.domain.ClientType;
import br.com.ieoafestasedecoracoes.partymanager.to.ClientTO;
import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
public class ClientObjects implements DomainObjectsToTest <ClientTO> {
	
	private final ClientTO objectById = new ClientTO(1, "123.456.789-01", "Clientid", "By Id", ClientType.LEGAL_PERSON, "client.byid@email.com", "byid");
	private final ClientTO objectToDelete = new ClientTO(1, "111.456.789-01", "Clientdel", "To Delete", ClientType.LEGAL_PERSON, "client.todelete@email.com", "todelete");
	private final ClientTO objectToUpdate = new ClientTO(1, "111.333.789-01", "Clientup", "To Update", ClientType.PHYSICAL_PERSON, "client.toupdate@email.com", "update");
	private final ClientTO objectUpdated = new ClientTO(1, "111.222.789-01", "Clientup", "Updated", ClientType.PHYSICAL_PERSON, "client.updated@email.com", "updated");
	private final ClientTO objectToCreate = new ClientTO(1, "111.888.999-01", "Clientcreate", "To Create", ClientType.LEGAL_PERSON, "client.tocreate@email.com", "create");
	
	private final String path = "/clients";
	private final String pathId = path + "/{id}";
	private final String objectName = "Client";
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		return Collections.unmodifiableMap(new HashMap<>());
	}

}
