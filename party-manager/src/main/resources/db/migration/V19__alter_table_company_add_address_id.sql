ALTER TABLE company ADD COLUMN address_id INTEGER NOT NULL;
ALTER TABLE company ADD CONSTRAINT company_address_fk FOREIGN KEY (address_id) REFERENCES address (id);