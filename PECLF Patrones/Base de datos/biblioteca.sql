CREATE TABLE "USUARIO"
(
    nombre varchar(50) NOT NULL,
    mail varchar(100) NOT NULL,
    pass varchar(50) NOT NULL,
    dni varchar(15) NOT NULL,
    edad integer NOT NULL,
    telefono integer NOT NULL,
    esadmin boolean NOT NULL,
    -- Tema castigos
    esta_castigado boolean NOT NULL,
    fecha_fin_castigo date, -- puede ser null si no ta castigao


    CONSTRAINT "USUARIO_PK" PRIMARY KEY(dni)
);

CREATE TABLE "LIBRO"
(
    id_libro integer NOT NULL,
    nombre varchar(100) NOT NULL,
    isbn varchar(25) NOT NULL,
    autor varchar(100) NOT NULL,
    edicion integer NOT NULL,
    prestado boolean NOT NULL,
    CONSTRAINT "LIBRO_PK" PRIMARY KEY(id_libro)
);

CREATE TABLE "PORTATIL"
(
    id_portatil integer NOT NULL,
    prestado boolean NOT NULL,
    marca varchar(20) NOT NULL,
    pulgadas integer NOT NULL,
    especificaciones varchar(150) NOT NULL,
    CONSTRAINT "PORTATIL_PK" PRIMARY KEY(id_portatil)
    
);

-- Usuario tiene prestado libro
CREATE TABLE "TIENE_PRESTADO_LIBRO"
(
    fecha_maxima_devolucion date NOT NULL,
    dni_usuario varchar(15) NOT NULL, --fk usuario
    id_libro integer NOT NULL, --fk libro
    CONSTRAINT "TIENE_PRESTADO_LIBRO_PK" PRIMARY KEY (dni_usuario, id_libro)
);

CREATE TABLE "TIENE_PRESTADO_PORTATIL"
(
    hora_maxima_devolucion time NOT NULL,
    dni_usuario varchar(15) NOT NULL, -- fk al usuario
    id_portatil integer NOT NULL, -- fk al portatil
    CONSTRAINT "TIENE_PRESTADO_PORTATIL_PK" PRIMARY KEY (dni_usuario)
);



ALTER TABLE "TIENE_PRESTADO_LIBRO" ADD CONSTRAINT prestamo_libro_usuario FOREIGN KEY (dni_usuario) REFERENCES "USUARIO" (dni);
ALTER TABLE "TIENE_PRESTADO_LIBRO" ADD CONSTRAINT prestamo_libro_libro FOREIGN KEY (id_libro) REFERENCES "LIBRO" (id_libro);

ALTER TABLE "TIENE_PRESTADO_PORTATIL" ADD CONSTRAINT prestamo_portatil_portatil FOREIGN KEY (id_portatil) REFERENCES "PORTATIL" (id_portatil);
ALTER TABLE "TIENE_PRESTADO_PORTATIL" ADD CONSTRAINT prestamo_portatil_usuario FOREIGN KEY (dni_usuario) REFERENCES "USUARIO" (dni);

