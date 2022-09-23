drop table if exists usuario;

create table usuario
(
    user_id bigint       not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    nombre     varchar(255) not null,
    edad       int          not null,
    cargo_id   bigint       not null,
    foreign key (cargo_id) references cargo(cargo_id),
    primary key (user_id)
) engine = InnoDB;