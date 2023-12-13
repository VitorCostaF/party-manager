CREATE TABLE material (
	id INT,
	name VARCHAR(50) NOT NULL,
	dimensions VARCHAR(20),
	type VARCHAR(20),
	quantity INT NOT NULL,
	company_id INT NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY material_company_fk (company_id) REFERENCES company (id)
);

CREATE TABLE material_seq (next_val INT);
INSERT INTO material_seq VALUES (1);