package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Decoration {

	@Id
	@GeneratedValue(generator = "decoration_seq")
	@SequenceGenerator(name = "decoration_seq", allocationSize = 1)
	private Integer id;
	private String name;
	private String theme;
	private Double price;
	private Double discount;
	
	@OneToMany(mappedBy = "decoration")
	private List<DecorationMaterial> decorationMaterials = new ArrayList<>();
	
	@OneToMany(mappedBy = "decoration")
	private List<RentDecoration> partyDecorations = new ArrayList<>();
}
