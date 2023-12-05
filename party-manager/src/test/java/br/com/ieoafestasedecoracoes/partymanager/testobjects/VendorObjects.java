package br.com.ieoafestasedecoracoes.partymanager.testobjects;

import org.springframework.stereotype.Component;

import br.com.ieoafestasedecoracoes.partymanager.controller.DomainObjectsToTest;
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
public class VendorObjects implements DomainObjectsToTest<VendorTO> {
	
	private VendorTO objectById = new VendorTO(1, "VendorId", "Byid", "vendor.by.email@email.com", "1234");
	private VendorTO objectToDelete = new VendorTO(1, "VendorDelete", "ToDelete", "vendor.to.delete@email.com", "12346");
	private VendorTO objectToUpdate = new VendorTO(1, "VendorUpdate", "Byid", "vendor.to.update@email.com", "1234");
	private VendorTO objectUpdated = new VendorTO(1, "Street Updated", "City Updated", "200", "Complement Updated");
	private VendorTO objectToCreate = new VendorTO(1, "Street Vendor Created", "City Vendor Created", "1234", "Complement Vendor Created");
	
	private String path = "/vendors";
	private String pathId = path + "/{id}";
	private String objectName = "Vendor";
	
	@Setter
	private String objectByIdJson;
	
}
