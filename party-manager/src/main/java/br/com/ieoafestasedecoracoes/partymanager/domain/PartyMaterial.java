package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class PartyMaterial {

	@Id
	@GeneratedValue(generator = "party_material_seq")
	@SequenceGenerator(name = "party_material_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "quantity_rented", nullable = false)
	private Integer quantityRented;
	
	@Column(name = "start_rent_date", nullable = false)
	private LocalDate startRentDate;
	
	@Column(name = "end_rent_date", nullable = false)
	private LocalDate endRentDate;
	
	@ManyToOne
	@JoinColumn(name = "material_id", referencedColumnName = "id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "party_id", referencedColumnName = "id")
	private Party party;
	
	
}
