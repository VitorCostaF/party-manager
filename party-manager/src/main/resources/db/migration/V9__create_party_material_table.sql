CREATE TABLE party_material (
	party_id INT,
	material_id INT,
	quantity_rented INT,
	start_rent_date DATETIME,
	end_rent_date DATETIME,
	
	PRIMARY KEY (party_id, material_id),
	
	CONSTRAINT fk_party_material_party FOREIGN KEY (party_id) 
	REFERENCES party(id),
	 
	CONSTRAINT fk_party_material_material FOREIGN KEY (material_id) 
	REFERENCES material(id)
);