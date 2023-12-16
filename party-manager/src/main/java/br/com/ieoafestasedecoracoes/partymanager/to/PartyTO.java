package br.com.ieoafestasedecoracoes.partymanager.to;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(value = Include.NON_NULL)
public class PartyTO {

	private Integer id;
	private LocalDate date;
	private Integer clientId;
	private Integer addressId;
	
}
