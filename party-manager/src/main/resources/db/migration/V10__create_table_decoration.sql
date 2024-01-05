CREATE TABLE decoration (
	id INT,
	name VARCHAR(30) NOT NULL,
	theme VARCHAR(30),
	price DECIMAL(6,2) NOT NULL,
	discount DECIMAL(6,2),
	
	PRIMARY KEY (id)
);

CREATE TABLE decoration_seq (next_val INT);
INSERT INTO decoration_seq VALUES (1);
