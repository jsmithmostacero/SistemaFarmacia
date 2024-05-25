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

INSERT INTO producto(nombre,descripcion,precio,cantidad,producto_imagen_url,producto_imagen_id,id_proveedor,id_tipo_producto) VALUES ('Apronax','Esta es una pastilla que da efecto a las 12 hrs.',4.00,45,'http://res.cloudinary.com/dxcsnwzxa/image/upload/v1707257135/wc0pvufgjahyjyqffjyv.jpg','wc0pvufgjahyjyqffjyv',1,2);

INSERT INTO producto(nombre,descripcion,precio,cantidad,producto_imagen_url,producto_imagen_id,id_proveedor,id_tipo_producto) VALUES ('Dolocordralan','Dolocordralan extra forte , analgésico antiflamatorio',4.50,50,'https://res.cloudinary.com/dxcsnwzxa/image/upload/v1707324578/uouhyqeylnhjvbe4xfdt.jpg','uouhyqeylnhjvbe4xfdt',1,2);

