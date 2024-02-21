package br.com.ieoafestasedecoracoes.partymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;

public interface DecorationRepository extends JpaRepository<Decoration, Integer>, JpaSpecificationExecutor<Decoration> {
	
	List<Decoration> findByNameContainingIgnoreCaseOrThemeContainingIgnoreCase(String name, String theme);
	List<Decoration> findByCategoriesId(Integer categoryId);
	List<Decoration> findByCompanyId(Integer compantyId);
	
	@Query("""
			SELECT d FROM Decoration d 
			JOIN
			(SELECT d.id as id, r.startRentDate as rendDate, COUNT(r.decorationId) cont FROM Decoration d 
			  JOIN d.rentDecorations r 
			  ON r.decorationId = d.id
			  GROUP BY d.id, r.startRentDate) t
			ON d.id = t.id
			ORDER BY t.rendDate DESC, t.cont DESC
			""")
	List<Decoration> findHotDecorations();
	
	List<Decoration> findByCompanyAddressProvinceId(Integer provinceId);

}
