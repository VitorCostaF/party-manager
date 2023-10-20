CREATE TABLE client (
	id INT,
	document VARCHAR(30) NOT NULL UNIQUE,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(100),
	client_type VARCHAR(20),
	email VARCHAR(100) not null UNIQUE,
	password VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (id)
);

CREATE TABLE client_seq (next_val INT);
INSERT INTO client_seq VALUES ( 1 );