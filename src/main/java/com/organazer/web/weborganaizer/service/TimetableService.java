package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.repository.TimetableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.organazer.web.weborganaizer.service.ReminderService.time;

@Service
public class TimetableService {
    private static TimetableRepository timetableRepository = null;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public void delete(LessonTimetable lessonTimetable){
        timetableRepository.delete(lessonTimetable);
    }
    public void save(LessonTimetable lessonTimetable){
        timetableRepository.save(lessonTimetable);
    }
    public List<LessonTimetable> findAllByIdUser(Long id){
        return timetableRepository.findAllByIdUser(id);
    }
    public List<LessonTimetable> findAllByIdUserAndNumberOfWeek(Long id, int week){return  timetableRepository.findAllByIdUserAndNumberOfWeek(id,week);}
    public List<LessonTimetable> getLessonsWeekByNumber(int week ,Long id) {
        LocalDateTime localeDate = LocalDateTime.now();
        localeDate = localeDate.plusWeeks(week);
        LocalDateTime firstSeptember = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 1, 1);
        List<LessonTimetable> lessonsList;
        if (ChronoUnit.WEEKS.between(firstSeptember, localeDate) % 2 == 0) {
            lessonsList = findAllByIdUserAndNumberOfWeek(id, 0);
        } else {
            lessonsList = findAllByIdUserAndNumberOfWeek(id, 1);
        }
        System.out.println(ChronoUnit.WEEKS.between(firstSeptember, localeDate));
        return lessonsList;
    }
    public static String getTodayDayOfWeek() {
        final Calendar c = Calendar.getInstance();
        String result = null;
        int i = c.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[6];
            case 2 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[0];
            case 3 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[1];
            case 4 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[2];
            case 5 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[3];
            case 6 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[4];
            case 7 -> result = Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK[5];
        }
        return result;
    }

    public LessonTimetable[][][] getSortLessonsTimetableAll(Long id) {
        TimetableService timetableService = new TimetableService(timetableRepository);
        List<LessonTimetable> lessonTimetableList = timetableService.findAllByIdUser(id);
        LessonTimetable[][][] lessonTimetables = new LessonTimetable[2][6][];
        for (int k = 0; k < 6; k++) {
            for (int j = 0; j < 2; j++) {
                ArrayList<LessonTimetable> lessonsList = new ArrayList<>();
//                for (LessonTimetable lessonTimetable : lessonTimetableList) {
//                    if (lessonTimetable.getDayOfWeek() == k && (lessonTimetable.getNumberOfWeek() == j || lessonTimetable.getNumberOfWeek() == 2)) {
//                        lessonsList.add(lessonTimetable);
//                    }
//                }

                if (lessonsList.size() > 1) {
                    sortLessonByTimeInDay(lessonsList);
                    LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                    for (int i = 0; i < lessonsList.size(); i++) {
                        timetables[i] = lessonsList.get(i);
                    }
                    lessonTimetables[j][k] = timetables;
                } else {
                    if (lessonsList.size() == 1) {
                        LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                        for (int i = 0; i < lessonsList.size(); i++) {
                            timetables[i] = lessonsList.get(i);
                        }
                        lessonTimetables[j][k] = timetables;
                    } else {
                        lessonTimetables[j][k] = null;
                    }
                }


            }
        }
        return lessonTimetables;
    }
    public LessonTimetable[][] getSortLessonsTimetableOneWeek(List<LessonTimetable> lessonTimetableList) {
        int maxSize = 0;
        LessonTimetable[][] lessonTimetables = new LessonTimetable[6][];
        for (int d = 0; d < 6; d++) {
            ArrayList<LessonTimetable> lessonsList = new ArrayList<>();
            for (LessonTimetable lessonTimetable : lessonTimetableList) {
                if (lessonTimetable.getDayOfWeek() == d ) {
                    lessonsList.add(lessonTimetable);
                }
            }
            if(maxSize < lessonsList.size()) maxSize = lessonsList.size();
            if (lessonsList.size() > 1) {
                sortLessonByTimeInDay(lessonsList);
                LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                for (int i = 0; i < lessonsList.size(); i++) {
                    timetables[i] = lessonsList.get(i);
                }
                lessonTimetables[d] = timetables;
            } else {
                if (lessonsList.size() == 1) {
                    LessonTimetable[] timetables = new LessonTimetable[lessonsList.size()];
                    for (int i = 0; i < lessonsList.size(); i++) {
                        timetables[i] = lessonsList.get(i);
                    }
                    lessonTimetables[d] = timetables;
                } else {
                    lessonTimetables[d] = null;
                }


            }
        }
        LessonTimetable[][] lessons = new LessonTimetable[6][maxSize];
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < maxSize; j++){
                if(j < lessonTimetables[i].length) {
                    lessons[i][j] = lessonTimetables[i][j];
                }else lessons[i][j] = new LessonTimetable();

            }
        }
        return lessons;
    }
    private  void sortLessonByTimeInDay(List<LessonTimetable> lessonsList){
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < lessonsList.size() - 1; i++) {
                if (time(lessonsList.get(i).getTime(), lessonsList.get(i + 1).getTime())) {
                    flag = true;
                    Collections.swap(lessonsList, i, i + 1);
                }
            }
        }
    }


}
