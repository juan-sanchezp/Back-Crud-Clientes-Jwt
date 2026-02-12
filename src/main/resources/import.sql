--para que me funcione con el update en propiertis 
INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica') ON CONFLICT (id) DO NOTHING;  
INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica') ON CONFLICT (id) DO NOTHING; 
INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica') ON CONFLICT (id) DO NOTHING;
INSERT INTO regiones (id, nombre) VALUES (4, 'Europa') ON CONFLICT (id) DO NOTHING;
INSERT INTO regiones (id, nombre) VALUES (5, 'Asia') ON CONFLICT (id) DO NOTHING;
INSERT INTO regiones (id, nombre) VALUES (6, 'Africa') ON CONFLICT (id) DO NOTHING;
INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía') ON CONFLICT (id) DO NOTHING;
INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida') ON CONFLICT (id) DO NOTHING;


INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (1, 'Colton', 'White', 'colton@gmail.com', '2024-01-01') ON CONFLICT (email) DO NOTHING;
INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (2, 'Steve', 'Jobs', 'sjobs@gmail.com', '2024-01-01') ON CONFLICT (email) DO NOTHING;
INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2024-01-01') ON CONFLICT (email) DO NOTHING;
INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (3, 'Ada', 'Wong', 'ada.lovelace@gmail.com', '2024-01-01') ON CONFLICT (email) DO NOTHING;
INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (5, 'Alan', 'Turing', 'alan.turing@gmail.com', '2024-01-01') ON CONFLICT (email) DO NOTHING;
INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (7, 'Grace', 'Hopper', 'grace.hopper@gmail.com', '2024-01-01') ON CONFLICT (email) DO NOTHING;

-- INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
-- INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
-- INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
-- INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
-- INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
-- INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
-- INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía');
-- INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida');

-- INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (1, 'Colton', 'White', 'colton@unicauca.edu.co', '2024-01-01');
-- INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (2, 'Steve', 'Jobs', 'sjobs@unicauca.edu.co', '2024-01-01');
-- INSERT INTO clientes (region_id, nombre, apellido, email, createAt) VALUES (4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.co', '2024-01-01');