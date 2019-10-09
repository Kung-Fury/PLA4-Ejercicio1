create database empresa;
use empresa;

create table if not exists proveedor (
    IDProveedor int auto_increment primary key,
    ProvNIF varchar(255) default '' not null,
    ProvNombre varchar(255) default '' not null,
    ProvDireccion varchar(255) default '' not null
) ENGINE=INNODB;

create table if not exists producto (
    IDProducto int auto_increment primary key,
    IDProveedor int,
    ProdNombre varchar(255) default '' not null,
    ProdPrecioUd decimal (19,2)
) ENGINE=INNODB;

create table if not exists pedido (
    IDPedido int auto_increment primary key,
    IDCliente int,
    IDProducto int
) ENGINE=INNODB;

create table if not exists cliente (
    IDCliente int auto_increment primary key,
    ClienteNombre varchar(255) not null,
    ClienteApellidos varchar(255) not null,
    ClienteDireccion varchar(255) not null,	
    ClienteTelefono varchar(255) not null
) ENGINE=INNODB;

ALTER TABLE producto
  ADD FOREIGN KEY fk1(IDProveedor) REFERENCES proveedor(IDProveedor) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE pedido
  ADD FOREIGN KEY fk2(IDCliente) REFERENCES cliente(IDCliente) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE pedido
  ADD FOREIGN KEY fk3(IDProducto) REFERENCES producto(IDProducto) ON DELETE RESTRICT ON UPDATE CASCADE;

insert into proveedor(ProvNombre)
VALUES	('Prov1'), 
		('Prov2');

insert into producto(ProdNombre, IDProveedor)
VALUES	('aaaaa', 2), 
		('bbbbb', 1);
        
       

