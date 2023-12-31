package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

	@OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE)
	private List<PartyMaterial> partyMaterials = new ArrayList<>();
	
	@OneToMany(mappedBy = "material")
	private List<DecorationMaterial> decorationMaterials = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

}
