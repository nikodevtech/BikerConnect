-- data.sql

INSERT INTO gestion_usuarios.usuarios (nombre_apellidos, email, contraseña, tlf_movil, cuenta_confirmada, rol, fch_registro)
VALUES
('Juan Pérez', 'juan.perez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '123456789', true, 'ROLE_USER', current_timestamp),
('María López', 'maria.lopez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '987654321', true, 'ROLE_USER', current_timestamp),
('Carlos Sánchez', 'carlos.sanchez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '555555555', true, 'ROLE_USER', current_timestamp),
('Laura Martínez', 'laura.martinez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '666666666', true, 'ROLE_USER', current_timestamp),
('Alejandro Rodríguez', 'alejandro.rodriguez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '777777777', true, 'ROLE_USER', current_timestamp),
('Ana García', 'ana.garcia@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '888888888', true, 'ROLE_USER', current_timestamp),
('Pedro Fernández', 'pedro.fernandez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '999999999', true, 'ROLE_USER', current_timestamp),
('Elena Ruiz', 'elena.ruiz@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '111111111', true, 'ROLE_USER', current_timestamp),
('Javier Gómez', 'javier.gomez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '222222222', true, 'ROLE_USER', current_timestamp),
('Sara Torres', 'sara.torres@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '333333333', true, 'ROLE_USER', current_timestamp),
('Daniel Navarro', 'daniel.navarro@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '444444444', true, 'ROLE_USER', current_timestamp),
('Isabel Ramírez', 'isabel.ramirez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '555555555', true, 'ROLE_USER', current_timestamp),
('Adrián González', 'adrian.gonzalez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '666666666', true, 'ROLE_USER', current_timestamp),
('Carmen López', 'carmen.lopez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '777777777', true, 'ROLE_USER', current_timestamp),
('Antonio García', 'antonio.garcia@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '888888888', true, 'ROLE_USER', current_timestamp),
('Lorena Martínez', 'lorena.martinez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '999999999', true, 'ROLE_USER', current_timestamp),
('Francisco Sánchez', 'francisco.sanchez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '111111111', true, 'ROLE_USER', current_timestamp),
('Natalia Fernández', 'natalia.fernandez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '222222222', true, 'ROLE_USER', current_timestamp),
('Miguel Ruiz', 'miguel.ruiz@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '333333333', true, 'ROLE_USER', current_timestamp),
('Silvia Gómez', 'silvia.gomez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '444444444', true, 'ROLE_USER', current_timestamp),
('Hugo Torres', 'hugo.torres@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '555555555', true, 'ROLE_USER', current_timestamp),
('Beatriz Ramírez', 'beatriz.ramirez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '666666666', true, 'ROLE_USER', current_timestamp),
('David González', 'david.gonzalez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '777777777', true, 'ROLE_USER', current_timestamp),
('Patricia López', 'patricia.lopez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '888888888', true, 'ROLE_USER', current_timestamp),
('Roberto García', 'roberto.garcia@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '999999999', true, 'ROLE_USER', current_timestamp),
('Claudia Martínez', 'claudia.martinez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '111111111', true, 'ROLE_USER', current_timestamp),
('José Sánchez', 'jose.sanchez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '222222222', true, 'ROLE_USER', current_timestamp),
('Alicia Fernández', 'alicia.fernandez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '333333333', true, 'ROLE_USER', current_timestamp),
('Manuel Gómez', 'manuel.gomez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '444444444', true, 'ROLE_USER', current_timestamp),
('Marina Martínez', 'marina.martinez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '555555555', true, 'ROLE_USER', current_timestamp),
('Raúl Sánchez', 'raul.sanchez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '666666666', true, 'ROLE_USER', current_timestamp),
('Eva López', 'eva.lopez@example.com', '$2a$10$RtSUJbLWmD6Xdt5atFdAB.SAlHSCWyryHEVGBlAzf0NG6XSDPXrW.', '777777777', true, 'ROLE_USER', current_timestamp);

INSERT INTO gestion_logicanegocio.motos (id_usuario_propietario, marca, modelo, año, color, desc_modificaciones)
VALUES
(2, 'Yamaha', 'YZF-R6', 2018, 'Negro', 'Escape Yoshimura, Cúpula Zero Gravity'),
(3, 'Kawasaki', 'Ninja ZX-10R', 2021, 'Verde', 'Suspensión Showa, Frenos Brembo'),
(4, 'Suzuki', 'GSX-R750', 2019, 'Azul', 'Escape Akrapovic, Amortiguador Öhlins'),
(5, 'Ducati', 'Panigale V4', 2020, 'Rojo', 'Suspensión Öhlins, Frenos Brembo'),
(6, 'Honda', 'CBR1000RR', 2022, 'Blanco', 'Escape Yoshimura, Cúpula Puig'),
(7, 'BMW', 'S1000RR', 2021, 'Gris', 'Suspensión Ohlins, Quickshifter'),
(8, 'Kawasaki', 'Ninja 650', 2019, 'Negro', 'Escape Akrapovic, Cúpula Puig'),
(9, 'Triumph', 'Street Triple RS', 2020, 'Blanco', 'Suspensión Öhlins, Frenos Brembo'),
(10, 'Yamaha', 'MT-09', 2021, 'Azul', 'Escape Akrapovic, Cúpula Puig'),
(16, 'Ducati', 'Monster 821', 2018, 'Rojo', 'Suspensión Öhlins, Frenos Brembo'),
(11, 'Suzuki', 'SV650', 2020, 'Negro', 'Escape Yoshimura, Cúpula Puig'),
(12, 'KTM', 'Duke 790', 2019, 'Naranja', 'Suspensión WP, Frenos Brembo'),
(15, 'Honda', 'CB500F', 2022, 'Blanco', 'Escape Akrapovic, Cúpula Puig'),
(13, 'Harley-Davidson', 'Street Rod', 2021, 'Negro', 'Asiento Biplaza, Alforjas'),
(14, 'Kawasaki', 'Z900', 2019, 'Verde', 'Escape Akrapovic, Cúpula Puig'),
(14, 'MV Agusta', 'Brutale 800', 2020, 'Rojo', 'Suspensión Öhlins, Frenos Brembo'),
(15, 'Ducati', 'Scrambler Icon', 2021, 'Amarillo', 'Escape Termignoni, Manillar Rizoma'),
(16, 'BMW', 'R1250GS', 2022, 'Gris', 'Suspensión ESA, Asientos Calefactables');

INSERT INTO gestion_logicanegocio.quedadas (desc_quedada, fch_hora_encuentro, lugar, estado, usuario_organizador)
VALUES
('Conocer personas cerca de San Sebastián', '2024-06-16 13:00:00', 'San Sebastián, aparcamientos playa Ondarreta', 'Planificada', 'juan.perez@example.com');

INSERT INTO gestion_logicanegocio.participantes (id_quedada, id_usuario)
VALUES
(1, 2), (1, 3), (1, 4), (1, 5),
(1, 6), (1, 7), (1, 8), (1, 9),
(1, 10), (1, 11), (1, 12), (1, 13),
(1, 14), (1, 15);

INSERT INTO gestion_logicanegocio.quedadas (desc_quedada, fch_hora_encuentro, lugar, estado, usuario_organizador)
VALUES
('Conocer personas cerca de Sevilla', '2024-02-11 16:00:00', 'Sevilla, estadio olimpico', 'Cancelada', 'maria.lopez@example.com');

INSERT INTO gestion_logicanegocio.quedadas (desc_quedada, fch_hora_encuentro, lugar, estado, usuario_organizador)
VALUES
('Quedada para conocer personas cerca de Jerez', '2024-02-12 17:00:00', 'Jerez, entrada circuito', 'Completada', 'carlos.sanchez@example.com');

INSERT INTO gestion_logicanegocio.participantes (id_quedada, id_usuario)
VALUES
(3, 2), (3, 3), (3, 4), (3, 5),
(3, 6), (3, 7), (3, 8), (3, 9);