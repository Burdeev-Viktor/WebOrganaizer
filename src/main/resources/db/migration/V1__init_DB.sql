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
    primary key (id_user)) engine=InnoDB;

create table grades (
    id bigint not null auto_increment,
    id_reminder bigint not null,
    grade smallint not null,
    primary key (id));

ALTER TABLE `organizer_db`.`lessons`
    ADD INDEX `id_user_for_lessons_idx` (`id_user` ASC) VISIBLE;
;
ALTER TABLE `organizer_db`.`lessons`
    ADD CONSTRAINT `id_user_for_lessons`
        FOREIGN KEY (`id_user`)
            REFERENCES `organizer_db`.`users` (`id_user`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `organizer_db`.`timetable`
    ADD INDEX `id_user_for_timetable_idx` (`id_user` ASC) VISIBLE;
;
ALTER TABLE `organizer_db`.`timetable`
    ADD CONSTRAINT `id_user_for_timetable`
        FOREIGN KEY (`id_user`)
            REFERENCES `organizer_db`.`users` (`id_user`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `organizer_db`.`lessons`
    ADD INDEX `id_lesson_name_id` (`name` ASC) VISIBLE;
;
ALTER TABLE `organizer_db`.`timetable`
    ADD INDEX `lesson_name_for_timetable_idx` (`name` ASC) VISIBLE;
;

ALTER TABLE `organizer_db`.`timetable`
    ADD CONSTRAINT `lesson_name_for_timetable`
        FOREIGN KEY (`name`)
            REFERENCES `organizer_db`.`lessons` (`name`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `organizer_db`.`reminders`
    ADD INDEX `id_user_for_reminder_idx` (`id_user` ASC) VISIBLE;
;
ALTER TABLE `organizer_db`.`reminders`
    ADD CONSTRAINT `id_user_for_reminder`
        FOREIGN KEY (`id_user`)
            REFERENCES `organizer_db`.`users` (`id_user`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `organizer_db`.`reminders`
    ADD INDEX `lesson_name_for_reminder_idx` (`lesson` ASC) VISIBLE;
;
ALTER TABLE `organizer_db`.`reminders`
    ADD CONSTRAINT `lesson_name_for_reminder`
        FOREIGN KEY (`lesson`)
            REFERENCES `organizer_db`.`lessons` (`name`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `organizer_db`.`grades`
    ADD INDEX `id_reminder_for_grades_idx` (`id_reminder` ASC) VISIBLE;
;
ALTER TABLE `organizer_db`.`grades`
    ADD CONSTRAINT `id_reminder_for_grades`
        FOREIGN KEY (`id_reminder`)
            REFERENCES `organizer_db`.`reminders` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;