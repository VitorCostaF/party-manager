package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyMaterialTO;
import lombok.Getter;
import lombok.Setter;

//@Component
@Getter
//@Slf4j
public class PartyMaterialObjects implements DomainObjectsToTest<PartyMaterialTO> {
	
	private PartyMaterialTO objectById = new PartyMaterialTO(1, 1, LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), 1, 1);
	private PartyMaterialTO objectToDelete = new PartyMaterialTO(1, 2, LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(7), 1, 1);
	private PartyMaterialTO objectToUpdate = new PartyMaterialTO(1, 1, LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), 1, 1);
	private PartyMaterialTO objectUpdated = new PartyMaterialTO(1, 4, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1), 1, 1);
	private PartyMaterialTO objectToCreate = new PartyMaterialTO(1, 6, LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), 1, 1);
	
	private String path = "/parties";
	private String pathId = path + "/{id}";
	private String objectName = "PartyMaterial";
	
	Map<String, DomainObjectInteface> dependenciesMap = new HashMap<>();
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		return Collections.unmodifiableMap(dependenciesMap);
	}
	
}
