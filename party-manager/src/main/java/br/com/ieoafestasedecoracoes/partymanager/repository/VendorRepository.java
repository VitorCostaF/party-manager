package br.com.ieoafestasedecoracoes.partymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{

	public Vendor findByEmail(String firstName);

	public Vendor findByFirstName(String firstName);

}
