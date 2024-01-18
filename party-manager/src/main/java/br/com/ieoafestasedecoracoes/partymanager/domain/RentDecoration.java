package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.ieoafestasedecoracoes.partymanager.domain.id.RentDecorationId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
	private LocalDateTime startRentDate;
	
	@Column(name = "end_rent_date", nullable = false)
	private LocalDateTime endRentDate;
	
	@Column(nullable = false)
	private Double price;
	
	@ManyToOne(optional = false)
	@MapsId("party_id")
	private Party party;
	
	@ManyToOne(optional = false)
	@MapsId("decoration_id")
	private Decoration decoration;

}
