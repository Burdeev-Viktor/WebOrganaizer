package com.organazer.web.weborganaizer.model;

import javax.persistence.*;

import com.organazer.web.weborganaizer.Const;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Entity
@Setter
@Getter
@Table(name = "timetable")
public class LessonTimetable implements Comparable<LessonTimetable> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "teacher")
    private String teacher;
    @Column(name = "room")
    private String room;
    @Column(name = "time")
    private String time;
    @Column(name = "type")
    private String type;
    @Column(name = "day_of_week")
    private String dayOfWeek;
    @Column(name = "number_of_week")
    private String numberOfWeek;

    public LessonTimetable() {

    }

    public LessonTimetable(Long id, Long idUser, String name, String teacher, String room, String time, String type, String dayOfWeek, String numberOfWeek) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    public LessonTimetable(String name, String teacher, String room, String time, String type, String dayOfWeek, String numberOfWeek) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    public LessonTimetable(Long id, String name, String teacher, String room, String time, String type, String dayOfWeek, String numberOfWeek) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
    }

    @Override
    public int compareTo(LessonTimetable lessonTimetable) {
        String[] time1array = this.getTime().split(Const.COLON);
        String[] time2array = lessonTimetable.getTime().split(Const.COLON);
        if((Integer.parseInt(time1array[0])) >= (Integer.parseInt(time2array[0]))){
            return 1;
        }
        return -1;
    }
}
