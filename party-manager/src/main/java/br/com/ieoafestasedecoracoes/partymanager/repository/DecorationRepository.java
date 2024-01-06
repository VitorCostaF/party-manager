package br.com.ieoafestasedecoracoes.partymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;

public interface DecorationRepository extends JpaRepository<Decoration, Integer> {

}
