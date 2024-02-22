CREATE TABLE decoration_advantage (
	decoration_id INT, 
	advantage_id INT,
	
	FOREIGN KEY decoration_advantage_decoration_fk (decoration_id) REFERENCES decoration (id),
	FOREIGN KEY decoration_advantage_advantage_fk (advantage_id) REFERENCES advantage (id)
);