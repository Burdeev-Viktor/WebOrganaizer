INSERT INTO users (login, name, password) values ('viktor', 'viktor', '[32, 44, -71, 98, -84, 89, 7, 91, -106, 75, 7, 21, 45, 35, 75, 112]');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Лабораторные', 1, 'БЖЧ', 'Экзамен');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Контрольные', 1, 'Методы машинного обучения', 'Зачёт');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Экономика', 'Зачёт');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Посещение', 1, 'Визуализация', 'Экзамен');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Автоматизация', 'Зачёт');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('Контрольные', 1, 'Оптимизация', 'Экзамен');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Облачные технологии', 'Зачёт');
INSERT INTO lessons (conditions, id_user, name, type_of_test) values ('', 1, 'Базы знаний', 'Экзамен');


insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Понедельник', 1, 'БЖЧ', 'Каждую', 'а.30.к.7', '','12:00', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Понедельник', 1, 'Методы машинного обоучения', 'Каждую', 'а.2П.к.17', 'Ковалёва','13:55', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Понедельник', 1, 'Экономика', 'Каждую', 'а.466.к.1', '','15:40', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Понедельник', 1, 'Визуализация', 'Каждую', 'а.216 к.11', '','17:45', 'Лабораторная');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Вторник', 1, 'Оптимизация', 'Каждую', 'а.30к.7', 'Ковалёва','15:40', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Вторник', 1, 'Оптимизация', 'Первая', 'а.316.к1', 'Ковалёва','17:45', 'Лабораторная');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Вторник', 1, 'БЖЧ', 'Вторая', 'а.368к.1', '','17:45', 'Лабораторная');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Среда', 1, 'Автоматизация', 'Каждую', 'а.316.к1', 'Кункевич','12:00', 'Лабораторная');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Среда', 1, 'Экономика', 'Каждую', 'а.301к.6', '','9:55', 'Практика');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Четверг', 1, 'Автоматизация', 'Каждую', 'а.314 к.1', 'Кункевич','15:40', 'Консультация');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Четверг', 1, 'Облачные технологии', 'Первая', 'а.314 к.1', 'Барышев','12:00', 'Лабораторная');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Четверг', 1, 'Методы машинного обоучения', 'Вторая', 'а.314 к.1', 'Чваньков','12:00', 'Лабораторная');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Пятница', 1, 'Облачные технологии', 'Первая', 'а.421к.1', 'Барышев','12:00', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Пятница', 1, 'Автоматизация', 'Вторая', 'а.421к.1', 'Кункевич','12:00', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Пятница', 1, 'Облачные технологии', 'Каждую', 'а.3П к.17', 'Барышев','13:55', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Пятница', 1, 'БЖЧ', 'Первая', 'а.372к.1', '','15:40', 'Практика');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Суббота', 1, 'Визуализация', 'Каждую', 'а.448к.1', 'Стальцова','12:00', 'Лекция');
insert into timetable (day_of_week, id_user, name, number_of_week, room, teacher, time, type)
    values ('Суббота', 1, 'Оптимизация', 'Каждую', 'а.212.к11', 'Ковалёва','15:40', 'Консультация');


insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'БЖЧ', 8, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, '2023-04-24', 'Вторник', 1, 'Оптимизация', 0, 'Курсовой', 'Каждую неделю', 1, '16:30');
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, '2023-04-25', 'Вторник', 1, 'Автоматизация', 0, 'Курсовой', 'Каждый день', 1, '16:00');
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Методы машинного обучения', 4, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Визуализация', 6, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Оптимизация', 4, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, null, null, 1, 'Базы знаний', 4, 'Лабораторные рыботы', null, 0, null);
insert into reminders (close_work, date, day_of_week, id_user, lesson, need_work, quest, setting_switch, switch, time)
    values (0, '2023-05-31', 'Невыбрано', 1, 'Оптимизация', 0, 'Диплом', 'Каждый день', 1, '12:00');
