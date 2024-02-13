package br.com.ieoafestasedecoracoes.partymanager.to;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class DecorationTO {

	private Integer id;
	private String name;
	private String theme;
	private BigDecimal price;
	private BigDecimal discount;
	private Integer companyId;
	private List<CategoryTO> categories = new ArrayList<>();
	
}
