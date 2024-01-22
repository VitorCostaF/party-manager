package br.com.ieoafestasedecoracoes.partymanager.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class CategoryTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;

}
