-- Administrador inicial con login "admin" y contraseña "admin"
INSERT INTO `ADMINISTRADOR` VALUES (1,'2013-11-24 01:04:42','admin','Administrador incial','ggm44T97GWJ6Rryx3do4yvl1bZ+gmUfG','ADMINISTRADOR','2013-11-24 01:04:42',1);

-- Medico con dni "11111111A", num. colegiado "11111" y contraseña "11111"
INSERT INTO `TIPOAGENDA` VALUES (1,'base',15,'14:00:00','08:00:00','MANANA',1);
INSERT INTO `CENTROSALUD` VALUES (1,'Centro salud pepe','988888888',1,'12345','C/. Pepe, nº 3','Ourense','Ourense');
INSERT INTO `MEDICO` VALUES (2,'Gomez Gomez','11111111A','a@a.com','2013-11-24 01:04:42','Antonio','11111','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','988123456','MEDICO','2013-11-24 01:04:42',1,1,1);

-- Paciente con dni "22222222B", num. tarjeta sanitaria "2222222222", num seg. social "2222222222222" y contraseña "22222"
INSERT INTO `PACIENTE` VALUES (3,'Benito Carmona','22222222B','b@b.com','2013-11-24 01:04:42','Ana','2222222222222','2222222222','','981123456','PACIENTE','2013-11-24 01:04:42',1,'12345','C/. jander clander, nº 2, 4º N','Coruña','Coruña',2);

-- Cita con fecha "2014-01-15" y hora "09:00:00"
INSERT INTO `CITA` VALUES (1, '15', 'PLANIFICADA', '2014-01-15', '09:00:00', 1, '2', '3');

-- Farmacia con nif "33333333C" y contraseña "33333"
INSERT INTO `FARMACIA` VALUES (4,'2013-11-24 01:04:42','33333333C','Farmacia de prueba','/QpUw+ZRH3ndoNb3N4gRpT5cz0C7pT9v','FARMACIA','2013-11-24 01:04:42',1,'12345','C/. farmacia, nº 2, 4º N','Coruña','Coruña');


