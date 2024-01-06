package br.com.ieoafestasedecoracoes.partymanager.domain;

import br.com.ieoafestasedecoracoes.partymanager.domain.id.DecorationMaterialId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DecorationMaterialId.class)
@Data
public class DecorationMaterial {
	
	@Id
	@Column(name = "material_id")
	private Integer materialId;
	
	@Id
	@Column(name = "decoration_id")
	private Integer decorationId;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@ManyToOne
	private Material material;
	
	@ManyToOne
	private Decoration decoration;

}
