package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DecorationMaterialId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer decorationId;
	private Integer materialId;
	
}
