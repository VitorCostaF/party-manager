package br.com.ieoafestasedecoracoes.partymanager.domain.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class RentDecorationId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer decorationId;
	private Integer partyId;
	
}
