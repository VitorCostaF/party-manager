ALTER TABLE address RENAME COLUMN province TO province_id;
ALTER TABLE address MODIFY COLUMN province_id INT;

ALTER TABLE address ADD CONSTRAINT address_province_fk FOREIGN KEY (province_id) REFERENCES province (id);