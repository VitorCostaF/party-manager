package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Material {

	@Id
	@GeneratedValue(generator = "material_seq")
	@SequenceGenerator(name = "material_seq", allocationSize = 1)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	private String dimensions;
	
	private String type;
	
	private Integer quantity;
	
	@OneToMany
	private List<PartyMaterial> partyMaterials = new ArrayList<>();
	
	@ManyToOne
	private Company companies;
	
}
