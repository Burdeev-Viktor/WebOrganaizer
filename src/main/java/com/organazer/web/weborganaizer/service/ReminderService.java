package com.organazer.web.weborganaizer.service;



import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Objects;

@Service
public class ReminderService {
    private  final ReminderRepository reminderRepository;
    private final LessonService lessonService;

    public ReminderService(ReminderRepository reminderRepository, LessonService lessonService) {
        this.reminderRepository = reminderRepository;
        this.lessonService = lessonService;
    }

    public List<Reminder> findAllRemindersEnableByIdUser(Long id){
        return reminderRepository.findAllBySwitchRAndIdUser(true,id);
    }
    public Reminder findOneByIdAndUserId(Long userId,Long id){
        List<Reminder> reminderList = reminderRepository.findAllByIdUser(userId);
        for (Reminder value : reminderList) {
            if (Objects.equals(value.getId(), id)) {
                return value;
            }
        }
        return null;
    }
    public void delete(Reminder reminder){
        reminderRepository.delete(reminder);
    }
    public void save(Reminder reminder){
        reminderRepository.save(reminder);
    }
    public List<Reminder> findAllByIdUser(Long id){
        return reminderRepository.findAllByIdUser(id);
    }
    public static boolean time(String time1,String time2){
        String[] time1array = time1.split(Const.COLON);
        String[] time2array = time2.split(Const.COLON);
        return ((Integer.parseInt(time1array[0])) >= (Integer.parseInt(time2array[0])));
    }
    public static LocalDate getLocalDateByString(String date) {
        String year = date.substring(0, 4);
        String mouth = date.substring(5, 7);
        String day = date.substring(8);
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(mouth), Integer.parseInt(day));
    }

    public static String getHours(String time) {
        return time.substring(0, 2);
    }

    public static String getMinutes(String time) {
        return time.substring(3);
    }

    private static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        return hours + Const.COLON + minutes;
    }

    public static long getMilSeconds(String dateStart) {
        SimpleDateFormat format = new SimpleDateFormat(Const.TINE_FORMAT);
        String dateStop = getCurrentTime();
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert d1 != null;
        assert d2 != null;
        return d1.getTime() - d2.getTime();
    }




}
