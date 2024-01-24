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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category implements DomainObjetctInterface {

	@Id
	@GeneratedValue(generator = "category_seq")
	@SequenceGenerator(name = "category_seq", allocationSize = 1)
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private List<Decoration> decorations = new ArrayList<>();
	
}
