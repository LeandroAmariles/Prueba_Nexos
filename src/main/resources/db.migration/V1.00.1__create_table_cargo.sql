drop table if exists cargo;

create table cargo
(
    cargo_id    bigint       not null auto_increment,
    created_at  datetime(6)  not null,
    is_active   bit          not null,
    updated_at  datetime(6)  null,
    nombre      varchar(50)  not null,
    primary key (cargo_id)
) engine = InnoDB;