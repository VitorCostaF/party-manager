package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Advantage {

	@Id
	@GeneratedValue(generator = "advantage_seq")
	@SequenceGenerator(name = "advantage_seq", allocationSize = 1)
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy = "advantages")
	private List<Decoration> decorations = new ArrayList<>();
	
}
