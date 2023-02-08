# Clientes MVC

![technology java](https://img.shields.io/badge/technology-java-blue.svg)

Implementación de un proyecto de software aplicando el patrón Modelo Vista Controlador (MVC) .

## Contenido

Script de creación de tabla
```
create table cliente(
id int auto_increment primary key,
Nombres varchar(100),
Correo varchar(100),
Telefono varchar(100)
)
```

## First steps

### How to create a clean mysql schemma (dev environment)
#### delete old schemma
```mysqladmin -uroot drop creditcard_purchase_dev -f```
#### create new schemma
```mysql -uroot -e "CREATE DATABASE creditcard_purchase_dev CHARACTER SET utf8 COLLATE utf8_general_ci;"```
#### Create tables
Check the following [src/api/storages/schema/schema.sql](src/api/storages/schema/schema.sql)

## Questions

* [credits-backend-10@mercadolibre.com](credits-backend-10@mercadolibre.com)
* [credits_backend@mercadolibre.com](credits_backend@mercadolibre.com)