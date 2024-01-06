package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.time.LocalDate;

import br.com.ieoafestasedecoracoes.partymanager.domain.id.RentDecorationId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@IdClass(RentDecorationId.class)
@Data
public class RentDecoration {
	
	@Id
	@Column(name = "party_id")
	private Integer partyId;
	
	@Id
	@Column(name = "decoration_id")
	private Integer decorationId;
	
	@Column(name = "start_rent_date", nullable = false)
	private LocalDate startRentDate;
	
	@Column(name = "end_rent_date", nullable = false)
	private LocalDate endRentDate;
	
	@Column(nullable = false)
	private Double price;
	
	@ManyToOne(optional = false)
	private Party party;
	
	@ManyToOne(optional = false)
	private Decoration decoration;

}
