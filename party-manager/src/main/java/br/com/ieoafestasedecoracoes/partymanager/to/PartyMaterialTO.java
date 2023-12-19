package br.com.ieoafestasedecoracoes.partymanager.to;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(value = Include.NON_NULL)
public class PartyMaterialTO implements DomainObjectInteface {

	private Integer id;
	private Integer quantityRented;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startRentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endRentDate;
	
	private Integer materialId;
	private Integer partyId;
	
}
