CREATE TABLE category (
	id INT,
	name VARCHAR(20),
	
	PRIMARY KEY (id)
);

CREATE TABLE category_seq (next_val INT);
INSERT INTO category_seq VALUES (1);