package br.com.ieoafestasedecoracoes.partymanager.domain.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PartyMaterialId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer materialId;
	private Integer partyId;

}
