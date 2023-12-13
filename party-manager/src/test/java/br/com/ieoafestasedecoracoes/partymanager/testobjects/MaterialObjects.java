package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import br.com.ieoafestasedecoracoes.partymanager.to.MaterialTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class MaterialObjects implements DomainObjectsToTest<MaterialTO> {
	
	private MaterialTO objectById = new MaterialTO(1, "Material By Id", "10 x 10", "Id", 1, 1);
	private MaterialTO objectToDelete = new MaterialTO(1, "Material To Delete", "15 x 10", "Plastic", 2, 1);
	private MaterialTO objectToUpdate = new MaterialTO(1, "Material To Update", "20 x 10", "Wood", 1, 1);
	private MaterialTO objectUpdated = new MaterialTO(1, "Material Updated", "Diameter 10 cm", "Plastic", 4, 1);
	private MaterialTO objectToCreate = new MaterialTO(1, "Material To Create", "10 x 10", "Plastic", 7, 1);
	
	private String path = "/materials";
	private String pathId = path + "/{id}";
	private String objectName = "Material";
	
	Map<String, DomainObjectInteface> dependenciesMap = new HashMap<>();
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		dependenciesMap.put("/companies", new CompanyTO(1, "Company To Materials", "12348"));
		return Collections.unmodifiableMap(dependenciesMap);
	}

	@Override
	public void setDepenciesId() {
		Integer id = dependenciesMap.get("/companies").getId();
		objectById.setCompanyId(id);	
		objectToDelete.setCompanyId(id);
		objectToUpdate.setCompanyId(id);
		objectUpdated.setCompanyId(id);
		objectToCreate.setCompanyId(id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			objectByIdJson = ((ObjectNode) mapper.readTree(objectByIdJson)).put("company-id", id).toString();
		} catch (JsonProcessingException e) {
			log.error("problema ao fazer update do company-id no objeto Material");
		}
	}
	
}
