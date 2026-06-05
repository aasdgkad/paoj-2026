DROP TABLE IF EXISTS publicatii;
DROP TABLE IF EXISTS utilizatori;

-- =========================
-- PUBLICATII
-- =========================
CREATE TABLE publicatii (
    id INT PRIMARY KEY AUTO_INCREMENT,

    titlu VARCHAR(255) NOT NULL,
    isbn VARCHAR(50) NOT NULL UNIQUE,
    autor VARCHAR(100) NOT NULL,
    categorie VARCHAR(50) NOT NULL,

    tip VARCHAR(20) NOT NULL, 
    -- "FIZICA" sau "DIGITALA"

    stare_fizica VARCHAR(50),
    format VARCHAR(50),
    marime_mb DOUBLE
);

-- =========================
-- UTILIZATORI
-- =========================
CREATE TABLE utilizatori (
    id VARCHAR(50) PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    tip VARCHAR(20) NOT NULL,
    salariu DOUBLE,
    data_angajare DATE
);

-- =========================
-- IMPRUMUTURI (RELATIE)
-- =========================
CREATE TABLE imprumuturi (
    id INT PRIMARY KEY AUTO_INCREMENT,

    utilizator_id VARCHAR(50),
    publicatie_id INT,

    data_imprumut DATE,

    returnat BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id),
    FOREIGN KEY (publicatie_id) REFERENCES publicatii(id)
);