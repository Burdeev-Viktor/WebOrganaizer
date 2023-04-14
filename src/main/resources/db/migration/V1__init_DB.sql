create table lessons (
    id bigint not null auto_increment,
    conditions varchar(255),
    id_user bigint,
    name varchar(50),
    type_of_test varchar(15),
    primary key (id)) engine=InnoDB;

create table reminders (
    id bigint not null auto_increment,
    close_work integer,
    date varchar(10),
    day_of_week varchar(15),
    id_user bigint,
    lesson varchar(50),
    need_work integer,
    quest varchar(255),
    setting_switch varchar(15),
    switch bit,
    time varchar(5), primary key (id)) engine=InnoDB;

create table timetable (
    id bigint not null auto_increment,
    day_of_week varchar(15),
    id_user bigint,
    name varchar(50),
    number_of_week varchar(10),
    room varchar(15),
    teacher varchar(25),
    time varchar(5),
    type varchar(15), primary key (id)) engine=InnoDB;

create table users (
    id_user bigint not null auto_increment,
    login varchar(55),
    name varchar(55),
    password varchar(255),
    primary key (id_user)) engine=InnoDB
