package br.com.ieoafestasedecoracoes.partymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;

public interface DecorationRepository extends JpaRepository<Decoration, Integer> {
	
	List<Decoration> findByNameContainingIgnoreCaseOrThemeContainingIgnoreCase(String name, String theme);
	List<Decoration> findByCategoriesId(Integer categoryId);
	List<Decoration> findByCompanyId(Integer compantyId);
	
	@Query("SELECT d FROM Decoration d JOIN d.rentDecorations r ORDER BY r.startRentDate")
	List<Decoration> findHotDecorations();

}
