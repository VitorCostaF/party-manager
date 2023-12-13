package br.com.ieoafestasedecoracoes.partymanager.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_NULL)
public class MaterialTO implements DomainObjectInteface {

	private Integer id;

	@Column(nullable = false)
	private String name;
	private String dimensions;
	private String type;
	private Integer quantity;
	
	@NotNull(message = "Deve haver uma empresa vinculada")
	private Integer companyId;
	
}
