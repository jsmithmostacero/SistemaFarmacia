insert into tipo_producto(nombre,descripcion) values ('Analgésicos','Analgésicos genéricas');
insert into tipo_producto(nombre,descripcion) values ('Antipiréticos','Antipiréticos genéricas'),
insert into tipo_producto(nombre,descripcion) values ('Antibióticos','Antibióticos genéricas');

insert into proveedor(nombre,contacto,direccion) values ('PharmaSupplier S.A.','(123) 456-7890 Avenida Secundaria');
insert into proveedor(nombre,contacto,direccion) values ('BioMed Solutions Ltd.','(123) 456-8880','123 Calle Principal');
insert into proveedor(nombre,contacto,direccion) values ('HealthCare Distributors Inc.','(123) 456-9980','123 Calle Principal');

insert into rol(nombre) values ('Administrador')
insert into rol(nombre) values ('Empleado')

insert into usuario(username,contrasenia) values ('admin','123')

insert into usuario_Roles(rol_id_rol, usuario_id_usuario) values (1, 1);

INSERT INTO producto (cantidad, precio, id_proveedor, id_tipo_producto, descripcion, nombre, producto_imagen_id,producto_imagen_url)
VALUES (45, 4.00, 1, 2, 'Esta es una pastilla que da efecto a las 12 hrs.', 'Apronax', 'wc0pvufgjahyjyqffjyv','http://res.cloudinary.com/dxcsnwzxa/image/upload/v1707257135/wc0pvufgjahyjyqffjyv.jpg');
