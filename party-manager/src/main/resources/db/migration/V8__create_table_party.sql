CREATE TABLE party (
	id INT,
	party_date DATETIME NOT NULL,
	client_id INT NOT NULL,
	address_id INT NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY party_client_fk (client_id) REFERENCES client (id),
	FOREIGN KEY party_address_fk (address_id) REFERENCES address (id)
);

CREATE TABLE party_seq (next_val INT);
INSERT INTO party_seq VALUES (1);