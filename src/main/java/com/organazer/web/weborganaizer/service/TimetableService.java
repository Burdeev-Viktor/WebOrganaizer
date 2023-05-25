package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.model.enums.DayOfWeek;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.enums.NumberWeek;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.model.enums.TypeOfLesson;
import com.organazer.web.weborganaizer.repository.ReminderRepository;
import com.organazer.web.weborganaizer.repository.TimetableRepository;
import com.organazer.web.weborganaizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TimetableService {
    private static UserRepository userRepository = null;
    private static TimetableRepository timetableRepository = null;
    private static  ReminderRepository reminderRepository = null;


    public TimetableService(TimetableRepository timetableRepository, UserRepository userRepository,ReminderRepository reminderRepository) {
        TimetableService.timetableRepository = timetableRepository;
        TimetableService.userRepository = userRepository;
        TimetableService.reminderRepository = reminderRepository;
    }

    public void delete(LessonTimetable lessonTimetable) {
        timetableRepository.delete(lessonTimetable);
    }

    private static void save(LessonTimetable lessonTimetable) {
        timetableRepository.save(lessonTimetable);
    }

    public List<LessonTimetable> findAllByIdUser(Long id) {
        return timetableRepository.findAllByIdUser(id);
    }

    public List<LessonTimetable> findAllByIdUserAndNumberOfWeek(Long id, String week) {
        return timetableRepository.findAllByIdUserAndNumberOfWeek(id, week);
    }

    public List<LessonTimetable> getLessonsWeekByNumber(int week, Long id) {
        LocalDateTime localeDate = LocalDateTime.now();
        localeDate = localeDate.plusWeeks(week);
        LocalDateTime firstSeptember = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 1, 1);
        List<LessonTimetable> lessonsList;
        if (ChronoUnit.WEEKS.between(firstSeptember, localeDate) % 2 == 0) {
            lessonsList = findAllByIdUserAndNumberOfWeek(id, NumberWeek.FIRST.toString());
        } else {
            lessonsList = findAllByIdUserAndNumberOfWeek(id, NumberWeek.SECOND.toString());
        }
        return lessonsList;
    }

    public LessonTimetable[][][] getSortLessonsTimetableAll(Long id) {
        int maxSize = 0;
        TimetableService timetableService = new TimetableService(timetableRepository, userRepository,reminderRepository);
        List<LessonTimetable> lessonTimetableList = timetableService.findAllByIdUser(id);
        LessonTimetable[][][] lessonTimetables = new LessonTimetable[2][6][];
        for (byte k = 0; k < 6; k++) {
            for (byte j = 0; j < 2; j++) {
                byte numberDay = k;
                byte numberWeek = j;
                List<LessonTimetable> lessonsList = lessonTimetableList
                        .stream()
                        .filter(lessonTimetable ->
                                Objects.equals(lessonTimetable.getDayOfWeek(),DayOfWeek.getSixDay()[numberDay]) &&
                                        (Objects.equals(lessonTimetable.getNumberOfWeek(), NumberWeek.values()[numberWeek]) ||
                                                Objects.equals(lessonTimetable.getNumberOfWeek(), NumberWeek.ALL)))
                        .collect(Collectors.toList());
                if (lessonsList.size() > maxSize) maxSize = lessonsList.size();
                if (lessonsList.size() >= 1) {
                    sortLessonByTimeInDay(lessonsList);
                    lessonTimetables[j][k] = lessonsList.toArray(LessonTimetable[]::new);
                }
            }
        }
        return lessonTimetables;
    }

    public LessonTimetable[][] getSortLessonsTimetableOneWeek(List<LessonTimetable> lessonTimetableList) {
        int maxSize = 0;
        LessonTimetable[][] lessonTimetables = new LessonTimetable[6][];
        for (byte d = 0; d < 6; d++) {
            byte day = d;
            List<LessonTimetable> lessonsList = lessonTimetableList.stream()
                    .filter(el -> Objects.equals(el.getDayOfWeek(), DayOfWeek.getSixDay()[day]))
                    .collect(Collectors.toList());
            if (maxSize < lessonsList.size()) maxSize = lessonsList.size();
            if (lessonsList.size() >= 1) {
                sortLessonByTimeInDay(lessonsList);
                lessonTimetables[d] = lessonsList.toArray(LessonTimetable[]::new);
            }
        }
        for (LessonTimetable[] days:lessonTimetables) {
            for (LessonTimetable lesson: days) {
                if(lesson.getType() == TypeOfLesson.LAB){
                    Reminder reminder = reminderRepository.getLabsByUserAndLesson(lesson.getIdUser(), lesson.getName());
                    if(reminder != null){
                        lesson.calLabStatistic(reminder);
                        lesson.setGradeStatistic(StatisticService.gradeStatistic(reminder));
                    }
                }
            }
        }
        return lessonTimetables;
    }

    private void sortLessonByTimeInDay(List<LessonTimetable> lessonsList) {
        List<LessonTimetable> sortedList = lessonsList.stream().sorted().toList();
        lessonsList.clear();
        lessonsList.addAll(sortedList);
    }

    public void saveByUserDetails(LessonTimetable lessonTimetable, UserDetails userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        lessonTimetable.setIdUser(user.getId());
        splitTime(lessonTimetable);
        save(lessonTimetable);
    }

    private static void splitTime(LessonTimetable lessonTimetable) {
        if (lessonTimetable.getTime().length() <= 6) {
            lessonTimetable.setTime(lessonTimetable.getTime().replaceAll(",", ""));
        } else {
            String[] times = lessonTimetable.getTime().split(",");
            lessonTimetable.setTime(times[1]);
        }
    }

    public void deleteByIdAndUserDetails(Long id, UserDetails userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Optional<LessonTimetable> lessonTimetable = timetableRepository.findById(id);
        if(lessonTimetable.isPresent()){
            if (Objects.equals(user.getId(), lessonTimetable.get().getIdUser())) {
                timetableRepository.delete(lessonTimetable.get());
            }
        }
    }
}
