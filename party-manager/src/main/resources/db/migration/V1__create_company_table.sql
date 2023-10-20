CREATE TABLE company (
	id INT,
	name VARCHAR(100),
	document VARCHAR(100),
	
	PRIMARY KEY(id)
);

CREATE TABLE company_seq (next_val INT);
INSERT INTO company_seq VALUES ( 1 );