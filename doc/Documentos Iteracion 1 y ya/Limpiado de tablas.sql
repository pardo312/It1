--------------------Clases-----------------------

CREATE TABLE Supermercado
(
NITSupermercado NUMBER(19) Primary Key ,
nombre varchar(50),
correoElectronico varchar(50),
direccion varchar(50)
);

CREATE TABLE Sucursal
(
ID NUMBER(19) Primary Key ,
nombre varchar(50),
ciudad varchar(50),
direccion varchar(50),
segmentacionDeMercado varchar(50),
tamanioInstalacion varchar(50),
--Llave Foranea de supermercado
NITSupermercado NUMBER(19),
FOREIGN KEY (NITSupermercado) REFERENCES Supermercado(NITSupermercado) on delete cascade
);

CREATE TABLE Proveedor
(
NIT NUMBER(19) Primary Key,
nombre varchar(50)
);

CREATE TABLE Pedido
(
ID NUMBER(19) Primary Key,
fechaEsperada DATE,
fechaEntrega DATE,
evaluacionCantidad  varchar(50),
evaluacionCalidad varchar(50),
calificacion FLOAT,
finalizado CHAR(1 byte),
--Llave Foranea de Proveedor
NITProveedor NUMBER(19),
FOREIGN KEY (NITProveedor) REFERENCES Proveedor(NIT) on delete cascade
);

CREATE TABLE ProductoProveedor
(
ID NUMBER(19) Primary Key,
nombre varchar(50),
precioUnidad FLOAT,
calificacionDeCalidad int,
fechaDeVencimiento  DATE,
cantidadUnidades int,
--LLave de Proveedor
NITProveedor NUMBER(19),
FOREIGN KEY (NITProveedor) REFERENCES Proveedor(NIT) on delete cascade
);

CREATE TABLE Producto
(
codigoDeBarras VARCHAR(50) Primary Key,
nombre VARCHAR(50),
marca VARCHAR(50),
precioUnitario FLOAT ,
presentacion VARCHAR(50),
precioPorUnidad FLOAT,
cantidadEnLaPresentacion FLOAT,
unidadDeMedida VARCHAR(50),
especificacionesDeEmpacado VARCHAR(100),
nivelDeReorden FLOAT,
--LLaves Foraneas Pedido, sucursal, bodega
IDPedido NUMBER(19),
IDSucursal NUMBER(19),
IDContenedor NUMBER(19),
IDPromocion NUMBER(19),
FOREIGN KEY (IDPedido) REFERENCES Pedido(ID) on delete cascade,
FOREIGN KEY (IDSucursal) REFERENCES Sucursal(ID) on delete cascade,
FOREIGN KEY (IDContenedor) REFERENCES Contenedor(ID) on delete cascade,
FOREIGN KEY (IDPromocion) REFERENCES Promocion(ID) on delete cascade
);

CREATE TABLE Categoria
(
ID NUMBER(19) Primary Key ,
nombreCategoria VARCHAR(50) ,
perecedero CHAR(1 byte),
codigoDeBarrasProducto VARCHAR(50),
FOREIGN KEY (codigoDeBarrasProducto) REFERENCES Producto(codigoDeBarras) on delete cascade
);

CREATE TABLE Factura
(
numeroFactura varchar(50) Primary Key  ,
fecha DATE,
idCliente NUMBER(19),
FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) on delete cascade
);

CREATE TABLE Contenedor
(
ID NUMBER(19) Primary Key ,
capacidadVolumen FLOAT,
capacidadPeso FLOAT,
unidadesPeso varchar(10),
unidadesVolumen VARCHAR(10),
--LLave Foranea de Sucursal
IDBodegaSucursal NUMBER(19),
FOREIGN KEY (IDBodegaSucursal) REFERENCES Sucursal(ID) on delete cascade
);

CREATE TABLE Estante
(
ID NUMBER(19) Primary Key ,
nivelAbastecimiento FLOAT,
--LLave Foranea de Bodega
IDSucursales NUMBER(19),
FOREIGN KEY (IDSucursales) REFERENCES Sucursal(ID) on delete cascade
);

CREATE TABLE TipoProducto
(
nombreTipo VARCHAR(50) Primary Key ,
metodoAlmacenamiento varchar(50),
--LLave Foranea de Categoria
IDCategoria NUMBER(19) ,
IDContenedor NUMBER(19),
FOREIGN KEY (IDCategoria) REFERENCES Categoria(ID)on delete cascade,
FOREIGN KEY (IDContenedor) REFERENCES Contenedor(ID)on delete cascade
);
----------Promociones-------------
CREATE TABLE Promocion
(
ID NUMBER(19) Primary Key ,
descripcion varchar(50),
precioPromocion VARCHAR(50),
--LLave Foranea de Estante
IDSucursal NUMBER(19),
FOREIGN KEY (IDSucursal) REFERENCES Sucursal(ID) on delete cascade
);

CREATE TABLE Paguexcantidadllevey
(
ID NUMBER(19) Primary Key ,
x int,
y int,
FOREIGN KEY (ID) REFERENCES Promocion(ID) on delete cascade
);

CREATE TABLE Paguexunidadesllevey
(
ID NUMBER(19) Primary Key ,
x int,
y int,
FOREIGN KEY (ID) REFERENCES Promocion(ID) on delete cascade
);

CREATE TABLE Descuentodelxporciento
(
ID NUMBER(19) Primary Key ,
porcentaje float,
FOREIGN KEY (ID) REFERENCES Promocion(ID) on delete cascade
);

CREATE TABLE Pague1llevesegundoaxporciento
(
ID NUMBER(19) Primary Key ,
porcentaje float,
FOREIGN KEY (ID) REFERENCES Promocion(ID) on delete cascade
);

--------Tablas Cliente(s)-----------------------------------------------------
CREATE TABLE ClienteNatural
(
cedula NUMBER(19) Primary Key ,
nombre varchar(50),
email varchar(50)
);

CREATE TABLE ClienteEmpresa
(
NIT varchar(50) Primary Key,
direccion varchar(50)
);

CREATE TABLE Cliente
(
idCliente NUMBER(19) Primary Key,
puntosDeCompra NUMBER(19),
--LLave Foranea de ClienteNatural y CLienteEmpresa
NITCliente varchar(50) ,
cedulaCliente NUMBER(19)   ,
FOREIGN KEY (NITCliente) REFERENCES ClienteEmpresa(NIT)  on delete cascade,
FOREIGN KEY (cedulaCliente) REFERENCES ClienteNatural(cedula) on delete cascade
);

---------------------RELACIONES------------------------
------------Relaciones con Producto-----------
CREATE TABLE FacturaProducto
(
--Llave de sucursal
numeroFactura varchar(50) ,
--Llave de proveedor
codigoDeBarrasProducto varchar(50)  ,
FOREIGN KEY (numeroFactura) REFERENCES Factura(numeroFactura) on delete cascade,
FOREIGN KEY (codigoDeBarrasProducto) REFERENCES Producto(codigoDeBarras) on delete cascade
);
-------Relacion Sucursal-Proovedor-----------------

CREATE TABLE SucursalProveedor
(
--Llave de sucursal
IDSucursal NUMBER(19)  ,
--Llave de proveedor
NITProveedor NUMBER(19)  ,
FOREIGN KEY (IDSucursal) REFERENCES Sucursal(ID) on delete cascade,
FOREIGN KEY (NITProveedor) REFERENCES Proveedor(NIT) on delete cascade
);

------Relaciones Supermercado-Cliente(s)-------------------------------------------
CREATE TABLE SupermercadoCliente
(
--Llave de supermercado
NITSupermercado NUMBER(19)  ,
--Llave de ClienteEmpresa
IDCliente NUMBER(19) ,
FOREIGN KEY (NITSupermercado) REFERENCES Supermercado(NITSupermercado) on delete cascade,
FOREIGN KEY (IDCliente) REFERENCES Cliente(idCliente)  on delete cascade
);
