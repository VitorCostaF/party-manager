CREATE TABLE address (
	id INT,
	street VARCHAR(100) NOT NULL,
	city VARCHAR(30) NOT NULL,
	zip_code VARCHAR(20) NOT NULL UNIQUE,
	complement VARCHAR(100),
	
	PRIMARY KEY (id) 
);

CREATE TABLE address_seq (next_val INT);
INSERT INTO address_seq VALUES ( 1 );