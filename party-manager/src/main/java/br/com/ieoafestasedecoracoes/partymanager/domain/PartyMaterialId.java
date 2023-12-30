package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class PartyMaterialId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer materialId;
	private Integer partyId;

}
