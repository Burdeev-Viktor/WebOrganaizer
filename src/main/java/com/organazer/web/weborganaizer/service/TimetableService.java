package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.repository.TimetableRepository;
import com.organazer.web.weborganaizer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.organazer.web.weborganaizer.service.ReminderService.time;

@Service
public class TimetableService {
    private static UserRepository userRepository = null;
    private static TimetableRepository timetableRepository = null;

    public TimetableService(TimetableRepository timetableRepository,UserRepository userRepository) {
        TimetableService.timetableRepository = timetableRepository;
        TimetableService.userRepository = userRepository;
    }

    public void delete(LessonTimetable lessonTimetable){
        timetableRepository.delete(lessonTimetable);
    }
    private static void save(LessonTimetable lessonTimetable){
        timetableRepository.save(lessonTimetable);
    }
    public List<LessonTimetable> findAllByIdUser(Long id){
        return timetableRepository.findAllByIdUser(id);
    }
    public List<LessonTimetable> findAllByIdUserAndNumberOfWeek(Long id, String week){return  timetableRepository.findAllByIdUserAndNumberOfWeek(id,week);}
    public List<LessonTimetable> getLessonsWeekByNumber(int week ,Long id) {
        LocalDateTime localeDate = LocalDateTime.now();
        localeDate = localeDate.plusWeeks(week);
        LocalDateTime firstSeptember = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 1, 1);
        List<LessonTimetable> lessonsList;
        if (ChronoUnit.WEEKS.between(firstSeptember, localeDate) % 2 == 0) {
            lessonsList = findAllByIdUserAndNumberOfWeek(id, Const.CHOICE_BOX_NUMBER_OF_WEEK[0]);
        } else {
            lessonsList = findAllByIdUserAndNumberOfWeek(id, Const.CHOICE_BOX_NUMBER_OF_WEEK[1]);
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
        int maxSize = 0;
        TimetableService timetableService = new TimetableService(timetableRepository,userRepository);
        List<LessonTimetable> lessonTimetableList = timetableService.findAllByIdUser(id);
        LessonTimetable[][][] lessonTimetables = new LessonTimetable[2][6][];
        for (byte k = 0; k < 6; k++) {
            for (byte j = 0; j < 2; j++) {
                byte numberDay = k;
                byte numberWeek = j;
                List<LessonTimetable> lessonsList = lessonTimetableList.stream()
                        .filter(lessonTimetable ->
                                Objects.equals(lessonTimetable.getDayOfWeek(), Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[numberDay]) &&
                                        (Objects.equals(lessonTimetable.getNumberOfWeek(), Const.CHOICE_BOX_NUMBER_OF_WEEK[numberWeek]) ||
                                                Objects.equals(lessonTimetable.getNumberOfWeek(), Const.CHOICE_BOX_NUMBER_OF_WEEK[2])))
                        .collect(Collectors.toList());
                if(lessonsList.size() > maxSize) maxSize = lessonsList.size();
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
                    .filter(el -> Objects.equals(el.getDayOfWeek(),Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[day]))
                    .collect(Collectors.toList());
            if(maxSize < lessonsList.size()) maxSize = lessonsList.size();
            if (lessonsList.size() >= 1) {
                sortLessonByTimeInDay(lessonsList);
                lessonTimetables[d] = lessonsList.toArray(LessonTimetable[]::new);
            }
        }
        return lessonTimetables;
    }
    private  void sortLessonByTimeInDay(List<LessonTimetable> lessonsList){
        List<LessonTimetable> sortedList = lessonsList.stream()
                .sorted()
                .toList();
        lessonsList.clear();
        lessonsList.addAll(sortedList);
    }
    public void saveByUserDetails(LessonTimetable lessonTimetable, UserDetails userDetails){
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        lessonTimetable.setIdUser(user.getId());
        if(lessonTimetable.getTime().length() > 5 ){
            splitTime(lessonTimetable);
        }
        save(lessonTimetable);
    }
    private static LessonTimetable splitTime(LessonTimetable lessonTimetable){
        String[] times = lessonTimetable.getTime().split(",");
        lessonTimetable.setTime(times[1]);
        return lessonTimetable;
    }

    public void deleteByIdAndUserDetails(Long id, UserDetails userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        LessonTimetable lessonTimetable = timetableRepository.getById(id);
        if(Objects.equals(user.getId(), lessonTimetable.getIdUser())){
            timetableRepository.delete(lessonTimetable);
        }
    }
}
