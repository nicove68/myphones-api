DROP TABLE IF EXISTS import_file_register;

CREATE TABLE import_file_register (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    imported_date VARCHAR(250) NOT NULL,
    file_name VARCHAR(250) NOT NULL
);

INSERT INTO import_file_register (id, imported_date, file_name) VALUES
    (1, '2010-07-02T16:00:00Z', 'south_afr_file'),
    (2, '2010-07-10T09:00:00Z', 'south_afr_telephones'),
    (3, '2010-07-12T22:00:00Z', 'south_afr_mobile');



DROP TABLE IF EXISTS mobile_number;

CREATE TABLE mobile_number (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    status_comment VARCHAR(250) NULLABLE,
    import_file_register_id INT NOT NULL
);

INSERT INTO mobile_number (number, status, status_comment, import_file_register_id) VALUES
    ('666123', 'VALID', null, 1),
    ('766123', 'VALID', null, 1),
    ('866123', 'VALID', null, 1),
    ('888888', 'VALID', null, 2),
    ('996123', 'INVALID', 'Starts with incorrect number', 2),
    ('667979', 'VALID', null, 3),
    ('669877', 'FIXED', 'Remove incorrect words', 3),
    ('7878787878', 'INVALID', 'Incorrect length', 3);

CREATE INDEX idx_import_file_register ON mobile_number (import_file_register_id);