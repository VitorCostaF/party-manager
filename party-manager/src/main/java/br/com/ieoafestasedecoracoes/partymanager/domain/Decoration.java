package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Decoration implements DomainObjetctInterface {

	@Id
	@GeneratedValue(generator = "decoration_seq")
	@SequenceGenerator(name = "decoration_seq", allocationSize = 1)
	private Integer id;
	private String name;
	private String theme;
	private Double price;
	private Double discount;
	
	@ManyToMany
	@JoinTable(name = "decoration_category",
			joinColumns = @JoinColumn(name = "decoration_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>(); 
	
	@OneToMany(mappedBy = "decoration")
	private List<DecorationMaterial> decorationMaterials = new ArrayList<>();
	
	@OneToMany(mappedBy = "decoration")
	private List<RentDecoration> rentDecorations = new ArrayList<>();
	
	@ManyToOne(optional = false)
	private Company company;
	
}
