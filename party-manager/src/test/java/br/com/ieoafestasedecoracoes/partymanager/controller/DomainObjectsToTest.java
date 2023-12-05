package br.com.ieoafestasedecoracoes.partymanager.controller;

import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;

public interface DomainObjectsToTest <T extends DomainObjectInteface> {

	String getPath();
	String getPathId();
	String getObjectName();
	
	T getObjectById();
	T getObjectToDelete();
	T getObjectToUpdate();
	T getObjectToCreate();
	T getObjectUpdated();
	
	String getObjectByIdJson();
	void setObjectByIdJson(String json);
	
}
