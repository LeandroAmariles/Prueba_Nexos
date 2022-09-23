drop table if exists producto;

create table producto
(
    producto_id      bigint        not null auto_increment,
    nombre_producto  varchar(50)   not null,
    cantidad         int           not null,
    user_id       bigint        not null,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    foreign key (user_id) references usuario(user_id),
    primary key (producto_id)
) engine = InnoDB;
