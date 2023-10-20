CREATE TABLE vendor (
	id INT,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(100),
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	company_id INT,
	
	PRIMARY KEY (id),
	FOREIGN KEY client_company_fk (company_id) REFERENCES company(id) 
);

CREATE TABLE vendor_seq (next_val INT);
INSERT INTO vendor_seq VALUES ( 1 );