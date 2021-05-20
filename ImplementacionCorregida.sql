Drop database if exists ShishaDragoon;
Create database if not exists ShishaDragoon;
use ShishaDragoon;
/*Creación de la tabla cuenta*/
create table if not exists cuenta
(codcuenta int unsigned not null AUTO_INCREMENT,
    correoelectronico varchar (25),
    contrasena varchar (15),
    constraint pk_cuenta primary key (codcuenta)
    );
/*Creación de la tabla usuario*/
create table if not exists usuario
	(codusuario int unsigned not null AUTO_INCREMENT,
    codcuenta int unsigned not null,
    nombreuser varchar (12),
    apellidouser varchar (24),
    direccionuser varchar (60),
    telefonouser char(12),
	cuentabancaria char(16),
    constraint pk_usuario primary key (codusuario),
    constraint fk_usuario_cuenta foreign key (codcuenta) references cuenta(codcuenta)
	on delete no action on update no action
    );
/*Creación de la tabla compra*/
create table if not exists compra
	(codcompra int unsigned not null AUTO_INCREMENT,
    fechacompra date,
    preciocompra decimal(8,2),
    codusuario int unsigned not null,
    constraint pk_compra primary key (codcompra),
    constraint fk_compra_usuario foreign key (codusuario) references usuario(codusuario)
	on delete no action on update no action
    );
/*Creación de la tabla producto*/
create table if not exists producto
	(codproducto int unsigned not null AUTO_INCREMENT,
    nombreproducto varchar (20),
    constraint pk_producto primary key (codproducto)
    );
/*Creación de la tabla detallepedido*/
create table if not exists detallepedido
	(codcompra int unsigned not null AUTO_INCREMENT,
    codproducto int unsigned not null,
    cantidadarticulos int,
    constraint pk_detallepedido primary key (codcompra,codproducto),
    constraint fk_detallepedido_producto foreign key (codproducto) references producto(codproducto)
	on delete no action on update no action,
    constraint fk_detallepedido_compra foreign key (codcompra) references compra(codcompra)
	on delete no action on update no action
    );
/*Creación de la tabla cachimbas*/
create table if not exists cachimba
	(codcachimba int unsigned not null AUTO_INCREMENT,
    preciocachimba decimal (6,2),
    marcacachimba varchar(15),
    modelocachimba varchar(15),
    stockcachimba int,
    codproducto int unsigned not null,
    constraint pk_cachimba primary key (codcachimba),
    constraint fk_cachimba_producto foreign key (codproducto) references producto(codproducto)
	on delete no action on update no action
    );
/*Creación de la tabla accesorios*/
create table if not exists accesorio
	(codaccesorio int unsigned not null AUTO_INCREMENT,
    precioaccesorio decimal (6,2),
    marcaaccesorio varchar(15),
    modeloaccesorioa varchar(15),
    stockaccesorio int,
    codproducto int unsigned not null,
    constraint pk_accesorio primary key (codaccesorio),
    constraint fk_accesorio_producto foreign key (codproducto) references producto(codproducto)
	on delete no action on update no action
	);
    
/*Introduccion de datso sishadragoon*/
insert into cuenta ( correoelectronico, contrasena)
values ( "pepe@gmail.com","tupepito"),
( "liuis@gmail.com","cereal"),
( "juannle@gmail.com","pluto"),
( "dorito@gmail.com","ceramico"),
( "metralleta@gmail.com","ternera"),
( "bola@gmail.com","letrero"),
( "perlita@gmail.com","iguana"),
( "perez@gmail.com","pepinillo");

insert into usuario (codcuenta,nombreuser,apellidouser,telefonouser,cuentabancaria)
values (1,"Pepe","Molina Duran",'640258945','750559874875'),
(3,"Juan","Lopez Reina",'659784589','698754875423'),
(2,"Luisa","Ruiz Ruiz",'695423365','587423698542'),
(4,"Manuel","Carrasco",'656563236','65218569854'),
(5,"Alba","Gonzalez",'693584583','632542536985'),
(6,"Roberto","Duarte berea",'69877458','699693652354'),
(7,"Javi","Mouzo",'658259533','75895642312'),
(8,"Marco","Nieto Moreno",'639636569','956321414253');

insert into compra (fechacompra,preciocompra,codusuario)
values ('2020/5/19',120.30,1),
('2019/2/5',61.45,2),
('2021/2/25',256.89,3),
('2021/12/26',98.36,3),
('2021/11/9',20.36,8),
('2021/9/9',36.25,6);

insert into producto (nombreproducto)
values ("Gestor calor"),
("Corta viento"),
("Limpia shisha"),
("Manguera Camuflaje"),
("Titanium mix"),
("Fibra aeroespacial"),
("Aguaman"),
("Regal max");

insert into detallepedido (codcompra, codproducto,cantidadarticulos)
values (1,1,1),
(2,2,7),
(3,3,5),
(5,7,8),
(4,6,3),
(4,4,6),
(2,5,3);

insert into accesorio ( precioaccesorio,marcaaccesorio,modeloaccesorioa,stockaccesorio,codproducto)
values (7.20,"kalaud","Aerox",10,1),
(4.50,"Relox","Turbo",25,2),
(3.10,"Torbal","Terx",7,3),
(12.30,"Templo","2",15,4);

insert into cachimba ( preciocachimba,marcacachimba,modelocachimba,stockcachimba,codproducto)
values (120,"Embery","Mini",10,5),
(420.50,"Rigal","Madera",5,6),
(250,"Steamulation","Pro",12,7),
(105.30,"Caesar","Elox",13,8);