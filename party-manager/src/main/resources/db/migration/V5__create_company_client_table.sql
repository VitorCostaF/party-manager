CREATE TABLE company_client (
	company_id INT,
	client_id INT,
	
	PRIMARY KEY (company_id, client_id),
	
	CONSTRAINT fk_company_client_company FOREIGN KEY (company_id) 
	REFERENCES company(id),
	 
	CONSTRAINT fk_company_client_client FOREIGN KEY (client_id) 
	REFERENCES client(id)
);