package br.com.ieoafestasedecoracoes.partymanager.specification;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DecorationSpecification {

	public static Specification<Decoration> hasProvince(Integer provinceId) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("company").get("address").get("province").get("id"), provinceId);
	}
	
	public static Specification<Decoration> hasCity(Integer cityId) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("company").get("address").get("city").get("id"), cityId);
	}
	
	public static Specification<Decoration> hasInitialValue(BigDecimal initialValue) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.ge(root.get("price"), initialValue);
	}
	
	public static Specification<Decoration> hasFinalValue(BigDecimal finalValue) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.le(root.get("price"), finalValue);
	}
	
	public static Specification<Decoration> decorationDescription(String name) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("name"), "%" + name + "%" );
	}
	
	public static Specification<Decoration> category(Integer categoryId) {
		return (root, query, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("categories").get("id"), categoryId);
	}
	
	public static Specification<Decoration> hasAdvantages(List<Integer> ids) {
		return (root, query, criteriaBuilder) ->  {
			In<Integer> in = criteriaBuilder.in(root.get("advantages").get("id"));
			ids.forEach(in::value);
			return in;
		};
	}
	
}
