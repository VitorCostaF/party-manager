CREATE TABLE province (

	id INT AUTO_INCREMENT,
	name VARCHAR(40) NOT NULL,
	acronym VARCHAR(2),
	external_api_id INT,
	
	PRIMARY KEY (id)
	
);

INSERT INTO province (acronym, name, external_api_id) VALUES ('AC', 'Acre', 12);
INSERT INTO province (acronym, name, external_api_id) VALUES ('AL', 'Alagoas', 27);
INSERT INTO province (acronym, name, external_api_id) VALUES ('AP', 'Amapá', 16);
INSERT INTO province (acronym, name, external_api_id) VALUES ('AM', 'Amazonas', 13);
INSERT INTO province (acronym, name, external_api_id) VALUES ('BA', 'Bahia', 29);
INSERT INTO province (acronym, name, external_api_id) VALUES ('CE', 'Ceará', 23);
INSERT INTO province (acronym, name, external_api_id) VALUES ('ES', 'Espírito Santo', 32);
INSERT INTO province (acronym, name, external_api_id) VALUES ('GO', 'Goiás', 52);
INSERT INTO province (acronym, name, external_api_id) VALUES ('MA', 'Maranhão', 21);
INSERT INTO province (acronym, name, external_api_id) VALUES ('MT', 'Mato Grosso', 51);
INSERT INTO province (acronym, name, external_api_id) VALUES ('MS', 'Mato Grosso do Sul', 50);
INSERT INTO province (acronym, name, external_api_id) VALUES ('MG', 'Minas Gerais', 31);
INSERT INTO province (acronym, name, external_api_id) VALUES ('PA', 'Pará', 15);
INSERT INTO province (acronym, name, external_api_id) VALUES ('PB', 'Paraíba', 25);
INSERT INTO province (acronym, name, external_api_id) VALUES ('PR', 'Paraná', 41);
INSERT INTO province (acronym, name, external_api_id) VALUES ('PE', 'Pernambuco', 26);
INSERT INTO province (acronym, name, external_api_id) VALUES ('PI', 'Piauí', 22);
INSERT INTO province (acronym, name, external_api_id) VALUES ('RJ', 'Rio de Janeiro', 33);
INSERT INTO province (acronym, name, external_api_id) VALUES ('RN', 'Rio Grande do Norte', 24);
INSERT INTO province (acronym, name, external_api_id) VALUES ('RS', 'Rio Grande do Sul', 43);
INSERT INTO province (acronym, name, external_api_id) VALUES ('RO', 'Rondônia', 11);
INSERT INTO province (acronym, name, external_api_id) VALUES ('RR', 'Roraima', 14);
INSERT INTO province (acronym, name, external_api_id) VALUES ('SC', 'Santa Catarina', 42);
INSERT INTO province (acronym, name, external_api_id) VALUES ('SP', 'São Paulo', 35);
INSERT INTO province (acronym, name, external_api_id) VALUES ('SE', 'Sergipe', 28);
INSERT INTO province (acronym, name, external_api_id) VALUES ('TO', 'Tocantins', 17);
INSERT INTO province (acronym, name, external_api_id) VALUES ('DF', 'Distrito Federal', 53);

