package com.organazer.web.weborganaizer.model;

import javax.persistence.*;

import com.organazer.web.weborganaizer.Const;
import lombok.Getter;
import lombok.Setter;


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
