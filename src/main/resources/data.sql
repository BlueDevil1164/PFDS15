-- Insertar algunas marcas
INSERT INTO Marcas (nombre, rate, electronicos_matricula) VALUES
('Apple', 5, 'E0000001'),
('Samsung', 5, 'E0000002'),
('Google', 4, 'E0000003'),
('OnePlus', 4, 'E0000004'),
('Xiaomi', 4, 'E0000005'),
('Sony', 5, 'E0000006'),
('Oppo', 4, 'E0000007'),
('Huawei', 3, 'E0000008'),
('Motorola', 3, 'E0000009'),
('Asus', 5, 'E0000010');

-- Insertar algunas categorias
INSERT INTO Categorias (id_categoria, categoria, abreviatura) VALUES
(1, 'Smartphones', 'SM'),
(2, 'Tablets', 'TB'),
(3, 'Laptops', 'LT'),
(4, 'Cámaras', 'CM'),
(5, 'Auriculares', 'AH'),
(6, 'Smartwatches', 'SW'),
(7, 'Accesorios', 'AC'),
(8, 'Electrodomésticos', 'ED'),
(9, 'Consolas de videojuegos', 'CV'),
(10, 'Televisores', 'TV');

-- Insertar algunos Proveedores
INSERT INTO Proveedor (proveedor) VALUES
('Amazon'),
('Best Buy'),
('Walmart'),
('AliExpress'),
('Newegg'),
('Target'),
('Apple Store'),
('B&H Photo Video'),
('MediaMarkt'),
('Fry\'s Electronics');

-- Insertar algunos Electronicos
INSERT INTO Electronicos (matricula, nombre, codigo, ffac, precio, id_categoria) VALUES
('E0000001', 'iPhone 15', 'IP001', '2024-01-15', 1599.99, 1),
('E0000002', 'Samsung Galaxy S24', 'SG001', '2024-02-10', 1450.50, 1),
('E0000003', 'Google Pixel 8', 'GP001', '2024-03-01', 1200.99, 1),
('E0000004', 'OnePlus 11', 'OP001', '2024-04-21', 999.99, 1),
('E0000005', 'Xiaomi 13 Pro', 'XM001', '2024-05-16', 1299.75, 1),
('E0000006', 'Sony Xperia 1 V', 'SX001', '2024-06-07', 1399.90, 1),
('E0000007', 'Oppo Find X6 Pro', 'OPX001', '2024-07-22', 1450.00, 1),
('E0000008', 'Huawei P60 Pro', 'HW001', '2024-08-13', 1189.60, 1),
('E0000009', 'Motorola Edge 40', 'ME001', '2024-09-25', 849.99, 1),
('E0000010', 'Asus ROG Phone 7', 'AR001', '2024-10-05', 1399.00, 1);

-- Insertar algunos Electronicos-Proveedores
INSERT INTO Electronicos_Proveedores (matricula, id_proveedor, anio) VALUES
('E0000001', 1, 2024),
('E0000002', 2, 2024),
('E0000003', 3, 2024),
('E0000004', 4, 2024),
('E0000005', 5, 2024),
('E0000006', 6, 2024),
('E0000007', 7, 2024),
('E0000008', 8, 2024),
('E0000009', 9, 2024),
('E0000010', 10, 2024);



