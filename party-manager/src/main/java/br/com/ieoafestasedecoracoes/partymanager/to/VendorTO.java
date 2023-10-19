package br.com.ieoafestasedecoracoes.partymanager.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.ieoafestasedecoracoes.partymanager.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public VendorTO(Vendor user) {
		if (user != null) {
			this.id = user.getId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.email = user.getEmail();
			this.password = user.getPassword();
		}
	}
	
	public static List<VendorTO> fromUserList(List<Vendor> userList) {
		List<VendorTO> listTO = new ArrayList<>();
		
		if(userList != null) {			
			listTO = userList.stream().map(VendorTO::new).toList();
		}
		
		return listTO;
	}

}
