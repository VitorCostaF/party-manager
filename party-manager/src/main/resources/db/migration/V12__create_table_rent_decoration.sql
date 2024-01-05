CREATE TABLE rent_decoration (
	decoration_id INT,
	party_id INT,
	start_rent_date DATETIME NOT NULL,
	end_rent_date DATETIME NOT NULL,
	price DECIMAL(6,2) NOT NULL,
	
	PRIMARY KEY (decoration_id, party_id),
	FOREIGN KEY rent_decoration_decoration_fk (decoration_id) 
	REFERENCES decoration (id),
	
	FOREIGN KEY rent_decoration_party_fk (party_id) 
	REFERENCES party (id)
	
);