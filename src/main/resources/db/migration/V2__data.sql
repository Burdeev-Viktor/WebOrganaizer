INSERT INTO users (login, name, password) values ('viktor', 'viktor', '[32, 44, -71, 98, -84, 89, 7, 91, -106, 75, 7, 21, 45, 35, 75, 112]');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Лабораторные', 1, 'БЖЧ', 'EXAM');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Контрольные', 1, 'Методы машинного обучения', 'CREDIT');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Экономика', 'CREDIT');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Посещение', 1, 'Визуализация', 'EXAM');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Автоматизация', 'CREDIT');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Контрольные', 1, 'Оптимизация', 'EXAM');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Облачные технологии', 'CREDIT');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Базы знаний', 'EXAM');


insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('MONDAY', 1, 'БЖЧ', 'ALL', 'а.30.к.7', '','12:00', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('MONDAY', 1, 'Методы машинного обоучения', 'ALL', 'а.2П.к.17', 'Ковалёва','13:55', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('MONDAY', 1, 'Экономика', 'ALL', 'а.466.к.1', '','15:40', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('MONDAY', 1, 'Визуализация', 'ALL', 'а.216 к.11', '','17:45', 'LAB');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('TUESDAY', 1, 'Оптимизация', 'ALL', 'а.30к.7', 'Ковалёва','15:40', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('TUESDAY', 1, 'Оптимизация', 'FIRST', 'а.316.к1', 'Ковалёва','17:45', 'LAB');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('TUESDAY', 1, 'БЖЧ', 'SECOND', 'а.368к.1', '','17:45', 'LAB');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('WEDNESDAY', 1, 'Автоматизация', 'ALL', 'а.316.к1', 'Кункевич','12:00', 'LAB');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('WEDNESDAY', 1, 'Экономика', 'ALL', 'а.301к.6', '','9:55', 'PRACTICE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('THURSDAY', 1, 'Автоматизация', 'ALL', 'а.314 к.1', 'Кункевич','15:40', 'CONSULTATION');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('THURSDAY', 1, 'Облачные технологии', 'FIRST', 'а.314 к.1', 'Барышев','12:00', 'LAB');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('THURSDAY', 1, 'Методы машинного обоучения', 'SECOND', 'а.314 к.1', 'Чваньков','12:00', 'LAB');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('FRIDAY', 1, 'Облачные технологии', 'FIRST', 'а.421к.1', 'Барышев','12:00', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('FRIDAY', 1, 'Автоматизация', 'SECOND', 'а.421к.1', 'Кункевич','12:00', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('FRIDAY', 1, 'Облачные технологии', 'ALL', 'а.3П к.17', 'Барышев','13:55', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('FRIDAY', 1, 'БЖЧ', 'FIRST', 'а.372к.1', '','15:40', 'PRACTICE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('SATURDAY', 1, 'Визуализация', 'ALL', 'а.448к.1', 'Стальцова','12:00', 'LECTURE');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('SATURDAY', 1, 'Оптимизация', 'ALL', 'а.212.к11', 'Ковалёва','15:40', 'CONSULTATION');


insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'БЖЧ', 8, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, '2023-04-24', 'TUESDAY', 1, 'Оптимизация', 0, 'Курсовой', 'EVERYWEEK', 1, '16:30');
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, '2023-04-25', 'TUESDAY', 1, 'Автоматизация', 0, 'Курсовой', 'EVERYDAY', 1, '16:00');
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Методы машинного обучения', 4, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Визуализация', 6, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Оптимизация', 4, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Базы знаний', 4, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, '2023-05-31', null, 1, 'Оптимизация', 0, 'Диплом', 'EVERYDAY', 1, '12:00');
