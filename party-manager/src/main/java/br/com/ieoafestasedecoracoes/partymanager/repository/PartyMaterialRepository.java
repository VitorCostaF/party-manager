package br.com.ieoafestasedecoracoes.partymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.PartyMaterial;
import br.com.ieoafestasedecoracoes.partymanager.domain.PartyMaterialId;
import java.util.List;


public interface PartyMaterialRepository extends JpaRepository<PartyMaterial, PartyMaterialId>{
	
	List<PartyMaterial> findByMaterialId(Integer materialId);
	List<PartyMaterial> findByPartyId(Integer partyId);
	
}
