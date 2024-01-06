package br.com.ieoafestasedecoracoes.partymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.DecorationMaterial;
import br.com.ieoafestasedecoracoes.partymanager.domain.id.DecorationMaterialId;
import java.util.List;


public interface DecorationMaterialRepository extends JpaRepository<DecorationMaterial, DecorationMaterialId> {
	
	List<DecorationMaterial> findByDecorationId(Integer decorationId);
	List<DecorationMaterial> findByMaterialId(Integer materialId);

}
