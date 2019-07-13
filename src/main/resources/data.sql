DROP TABLE IF EXISTS import_file_register;

CREATE TABLE import_file_register (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    imported_date VARCHAR(250) NOT NULL,
    file_name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS mobile_number;

CREATE TABLE mobile_number (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    validation_comment VARCHAR(250) DEFAULT NULL,
    import_file_register_id INT NOT NULL
);

CREATE INDEX idx_import_file_register ON mobile_number (import_file_register_id);