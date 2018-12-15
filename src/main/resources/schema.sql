



DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
                       id INT NOT NULL AUTO_INCREMENT,
                       nombres VARCHAR(100) NOT NULL,
                       paterno VARCHAR(100) NOT NULL,
                       materno VARCHAR(100) NOT NULL,
                       edad integer,
                       email VARCHAR(100) NULL,
                       PRIMARY KEY (id));

