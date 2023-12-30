package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@IdClass(PartyMaterialId.class)
public class PartyMaterial {
	
	@Id
	@Column(name = "material_id")
	private Integer materialId;
	
	@Id
	@Column(name = "party_id")
	private Integer partyId;
	
	@Column(name = "quantity_rented", nullable = false)
	private Integer quantityRented;
	
	@Column(name = "start_rent_date", nullable = false)
	private LocalDate startRentDate;
	
	@Column(name = "end_rent_date", nullable = false)
	private LocalDate endRentDate;
	
	@ManyToOne
	@JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "party_id", referencedColumnName = "id", nullable = false)
	private Party party;
	
}
