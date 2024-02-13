package br.com.ieoafestasedecoracoes.partymanager.to;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startRentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endRentDate;
	
	private BigDecimal price;

}
