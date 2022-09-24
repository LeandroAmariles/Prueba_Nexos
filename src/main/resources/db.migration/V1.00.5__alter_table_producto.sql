alter table producto
add(
    fecha_ingreso     datetime(6) not null,
    usuario_modifico  varchar(45) null
), Engine = InnoDb