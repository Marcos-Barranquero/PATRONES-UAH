-- Usuarios
INSERT INTO USUARIO VALUES('admin','admin','admin','admin',21, 915432121, true, false, null);
INSERT INTO USUARIO VALUES('Marcos','marcos@correo.es','contrasena123','51129104N',21, 684432645, false, false, null);
INSERT INTO USUARIO VALUES('Eduardo','edu@correo.es','contrasena456','03212337L',21, 684321674, false, false, null);
INSERT INTO USUARIO VALUES('Juan','juan@yahoo.es','passguord','06011820B',19, 648347193, false, false, null);
INSERT INTO USUARIO VALUES('Alfonso','stickerinas@alcala.es','xiaomiestamuybien','16009589W',24, 684532740, false, false, null);


-- Libros
INSERT INTO LIBRO VALUES(1, 'Juego de tronos', '9788408221937', 'George R. Martin', 3, false);
INSERT INTO LIBRO VALUES(2, 'Una España mejor', '9788420438498', 'Mariano Rajoy', 1, false);
INSERT INTO LIBRO VALUES(3, 'Don Quijote', '9788433980571', 'Belén Estéban', 2, false);
INSERT INTO LIBRO VALUES(4, 'Patrones Software', '9788498389852', 'Salvador Otón', 5, false);
INSERT INTO LIBRO VALUES(5, 'Patrones Software', '9788498389852', 'Salvador Otón', 5, false);
INSERT INTO LIBRO VALUES(6, 'Quien se ha llevado mi queso', '9788467058147', 'Francisco Roig Ballester', 5, false);
INSERT INTO LIBRO VALUES(7, 'Como ser bueno en las finanzas', '9788491816614', 'Mario Conde', 3, false);
INSERT INTO LIBRO VALUES(8, 'El principito', '9788408221938', 'Su autor', 8, false);
INSERT INTO LIBRO VALUES(9, 'Spiderman', '9788408221932', 'Stan Lee', 2, false);

-- Portátiles

INSERT INTO PORTATIL VALUES(1, false, 'Apple', 13, 'Procesador: i5-6500U; Gráfica: Intel HD Graphics 630; SSD 240GB; Pantalla 720p.');
INSERT INTO PORTATIL VALUES(2, false, 'Apple', 13, 'Procesador: i5-6500U; Gráfica: Intel HD Graphics 630; SSD 240GB; Pantalla 720p.');
INSERT INTO PORTATIL VALUES(3, false, 'Apple', 13, 'Procesador: i5-6500U; Gráfica: Intel HD Graphics 630; SSD 240GB; Pantalla 720p.');
INSERT INTO PORTATIL VALUES(4, false, 'Asus', 15, 'Procesador: i7-8700U; Gráfica: Nvidia MX 250; SSD 512GB; Pantalla 1080p.');
INSERT INTO PORTATIL VALUES(5, false, 'Asus', 15, 'Procesador: i7-8700U; Gráfica: Nvidia MX 250; SSD 512GB; Pantalla 1080p.');
INSERT INTO PORTATIL VALUES(6, false, 'Asus', 15, 'Procesador: i7-8700U; Gráfica: Nvidia MX 250; SSD 512GB; Pantalla 1080p.');

-- Usuario Alfonso toma Juego de tronos prestado el 21 de diciembre, tiene hasta el 21 de enero para devolverlo.
UPDATE LIBRO SET prestado = true where id_libro = 1;
INSERT INTO TIENE_PRESTADO_LIBRO VALUES('2020-01-21', '16009589W', 1);

-- Usuario Marcos toma prestado el portátil 5 de asus y lo tiene que devolver el 21 de enero como tarde a las 17:30.
UPDATE PORTATIL SET prestado = true where id_portatil = 5;
INSERT INTO TIENE_PRESTADO_PORTATIL VALUES('17:30', '51129104N', 5);

-- Usuario Marcos toma prestado Quien se ha llevado mi queso el 14 de enero y lo tiene que devolver el 14 de febrero.
UPDATE LIBRO SET prestado = true where id_libro = 6;
INSERT INTO TIENE_PRESTADO_LIBRO VALUES('2020-02-14', '51129104N', 6);