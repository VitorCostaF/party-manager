CREATE TABLE city (
	id INT,
	name VARCHAR(100),
	external_id INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE city_seq (next_val INT);
INSERT INTO city_seq VALUES ( 1 );