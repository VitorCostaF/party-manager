package br.com.ieoafestasedecoracoes.partymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
