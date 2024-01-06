package br.com.ieoafestasedecoracoes.partymanager.to;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(value = Include.NON_NULL)
public class RentDecorationTO {
	
	private Integer partyId;	
	private Integer decorationId;
	private LocalDate startRentDate;
	private LocalDate endRentDate;
	private Double price;

}
