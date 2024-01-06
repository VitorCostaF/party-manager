package br.com.ieoafestasedecoracoes.partymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.RentDecoration;
import br.com.ieoafestasedecoracoes.partymanager.domain.id.RentDecorationId;

public interface RentDecorationRepository extends JpaRepository<RentDecoration, RentDecorationId> {

	List<RentDecoration> findByPartyId(Integer materialId);
	List<RentDecoration> findByDecorationId(Integer decorationId);

}
