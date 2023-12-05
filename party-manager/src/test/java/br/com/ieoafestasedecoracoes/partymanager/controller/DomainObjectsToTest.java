package br.com.ieoafestasedecoracoes.partymanager.controller;

import java.util.Map;

import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;

// TODO respeitar o ISP por conta do setDependencyID
public interface DomainObjectsToTest <T extends DomainObjectInteface> {

	String getPath();
	String getPathId();
	String getObjectName();
	String getObjectByIdJson();
	
	T getObjectById();
	T getObjectToDelete();
	T getObjectToUpdate();
	T getObjectToCreate();
	T getObjectUpdated();
		
	Map<String, DomainObjectInteface> getDependencyObjects();
	
	void setObjectByIdJson(String json);
	default void setDepenciesId() {};
	
}
