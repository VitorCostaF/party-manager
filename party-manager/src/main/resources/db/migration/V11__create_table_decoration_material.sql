CREATE TABLE decoration_material (
	material_id INT,
	decoration_id INT,
	quantity INT,
	
	PRIMARY KEY (material_id, decoration_id),
	FOREIGN KEY decoration_material_material_fk (material_id) 
	REFERENCES material (id),
	
	FOREIGN KEY decoration_material_decoration_fk (decoration_id) 
	REFERENCES decoration (id)
	
);