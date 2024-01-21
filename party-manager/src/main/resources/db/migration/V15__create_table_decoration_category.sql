CREATE TABLE decoration_category (
	decoration_id INT,
	category_id INT,
	
	PRIMARY KEY (decoration_id, category_id),
	FOREIGN KEY decoration_category_decoaration_fk (decoration_id) 
	REFERENCES decoration (id),
	FOREIGN KEY decoration_category_category_fk (category_id) 
	REFERENCES category (id)
);