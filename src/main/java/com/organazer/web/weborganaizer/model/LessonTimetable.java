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
    @Enumerated(EnumType.STRING)
    private TypeOfLesson type;
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    @Column(name = "number_of_week")
    @Enumerated(EnumType.STRING)
    private NumberWeek numberOfWeek;

    public LessonTimetable() {

    }
    @Override
    public int compareTo(LessonTimetable lessonTimetable) {
        String[] time1array = this.getTime().split(Const.COLON);
        String[] time2array = lessonTimetable.getTime().split(Const.COLON);
        if((Integer.parseInt(time1array[0])) > (Integer.parseInt(time2array[0]))){
            return 1;
        }else if((Integer.parseInt(time1array[0])) < (Integer.parseInt(time2array[0]))){
            return -1;
        }
        return 0;
    }
}
