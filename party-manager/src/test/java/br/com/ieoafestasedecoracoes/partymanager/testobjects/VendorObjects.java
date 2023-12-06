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
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class VendorObjects implements DomainObjectsToTest<VendorTO> {
	
	private VendorTO objectById = new VendorTO(1, "VendorId", "Byid", "vendor.by.email@email.com", "1234", 1);
	private VendorTO objectToDelete = new VendorTO(1, "VendorDelete", "ToDelete", "vendor.to.delete@email.com", "12346", 1);
	private VendorTO objectToUpdate = new VendorTO(1, "VendorUpdate", "Byid", "vendor.to.update@email.com", "1234", 1);
	private VendorTO objectUpdated = new VendorTO(1, "VendorUpdated", "Updated", "vendor.updated@email.com", "1234", 1);
	private VendorTO objectToCreate = new VendorTO(1, "VendorCreated", "Created", "vendor.created@email.com", "13245", 1);
	
	private String path = "/vendors";
	private String pathId = path + "/{id}";
	private String objectName = "Vendor";
	
	Map<String, DomainObjectInteface> dependenciesMap = new HashMap<>();
	
	@Setter
	private String objectByIdJson;

	@Override
	public Map<String, DomainObjectInteface> getDependencyObjects() {
		dependenciesMap.put("/companies", new CompanyTO(1, "Company To Vendors", "12348"));
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
			log.error("problema ao fazer update do company-id no objeto Vendor");
		}
	}
	
}
