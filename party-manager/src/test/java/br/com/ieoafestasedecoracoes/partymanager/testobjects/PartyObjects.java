package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.domain.ClientType;
import br.com.ieoafestasedecoracoes.partymanager.to.AddressTO;
import br.com.ieoafestasedecoracoes.partymanager.to.ClientTO;
import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class PartyObjects implements DomainObjectsToTest<PartyTO> {
	
	private PartyTO objectById = new PartyTO(1, LocalDateTime.now().plusDays(1), 1, 1);
	private PartyTO objectToDelete = new PartyTO(1, LocalDateTime.now().plusDays(3), 1, 1);
	private PartyTO objectToUpdate = new PartyTO(1, LocalDateTime.now().plusDays(6), 1, 1);
	private PartyTO objectUpdated = new PartyTO(1, LocalDateTime.now().plusDays(1), 1, 1);
	private PartyTO objectToCreate = new PartyTO(1, LocalDateTime.now().plusDays(3), 1, 1);
	
	private String path = "/parties";
	private String pathId = path + "/{id}";
	private String objectName = "Party";
	
	Map<String, DomainObjectInteface> dependenciesMap = new HashMap<>();
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		dependenciesMap.put("/addresses", new AddressTO(1, "Street to Party", "City to Party", "12345", "Complement to Party"));
		dependenciesMap.put("/clients", new ClientTO(1, "923.456.789-01", "Clientparty", "To Party", ClientType.LEGAL_PERSON, "client.toparty@email.com", "clientparty"));
		return Collections.unmodifiableMap(dependenciesMap);
	}

	@Override
	public void setDepenciesId() {
		Integer idAddress = dependenciesMap.get("/addresses").getId();
		Integer idClient = dependenciesMap.get("/clients").getId();
		objectById.setAddressId(idAddress);
		objectById.setClientId(idClient);
		objectToDelete.setAddressId(idAddress);
		objectToDelete.setClientId(idClient);
		objectToUpdate.setAddressId(idAddress);
		objectToUpdate.setClientId(idClient);
		objectUpdated.setAddressId(idAddress);
		objectUpdated.setClientId(idClient);
		objectToCreate.setAddressId(idAddress);
		objectToCreate.setClientId(idClient);
		ObjectMapper mapper = new ObjectMapper();
		try {
			objectByIdJson = ((ObjectNode) mapper.readTree(objectByIdJson))
					.put("address-id", idAddress).put("client-id", idClient).toString();
		} catch (JsonProcessingException e) {
			log.error("problema ao fazer update do address-id ou client-id no objeto Party");
		}
	}
	
}
