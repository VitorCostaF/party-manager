package br.com.ieoafestasedecoracoes.partymanager.to;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(value = Include.NON_NULL)
public class PartyTO implements DomainObjectInteface {

	private Integer id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	
	@NotNull(message = "a festa deve ter um cliente vinculado")
	private Integer clientId;
	
	@NotNull(message = "a festa deve ter um endereço vinculado")
	private Integer addressId;
	
}
