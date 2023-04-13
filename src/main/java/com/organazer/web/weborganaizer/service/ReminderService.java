package com.organazer.web.weborganaizer.service;



import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.repository.ReminderRepository;
import com.organazer.web.weborganaizer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
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
    private  final UserRepository userRepository;
    private final LessonService lessonService;

    public ReminderService(ReminderRepository reminderRepository, UserRepository userRepository, LessonService lessonService) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
        this.lessonService = lessonService;
    }

    public List<Reminder> findAllRemindersEnableByIdUser(Long id){
        return reminderRepository.findAllBySwitchRAndIdUser(true,id);
    }
    public Reminder findOneByIdAndUserId(Long userId,Long id){
        List<Reminder> reminderList = reminderRepository.findAllByIdUser(userId);
         return reminderList.stream()
                 .filter(element->Objects.equals(element.getId(),id))
                 .findFirst()
                 .orElse(null);
    }
    private void deleteById(Long id){
        reminderRepository.deleteById(id);
    }
    public void deleteByIdForUserDetails(Long id, UserDetails userDetails){
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Reminder reminder = reminderRepository.findReminderById(id);
        if(Objects.equals(user.getId(), reminder.getIdUser())){
            reminderRepository.deleteById(id);
        }else {
            System.out.println("Попытка удалить не своё напоминание:\n'пользователем: " + user.getLogin() + "\nc id " + user.getId() + "\nнапоминание: " + reminder);
        }

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
    public static boolean time(LessonTimetable time1, LessonTimetable time2){
        String[] time1array = time1.getTime().split(Const.COLON);
        String[] time2array = time2.getTime().split(Const.COLON);
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
    public void closeOneWork(Long id,UserDetails userDetails){
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Reminder reminder = reminderRepository.findReminderById(id);
        if(Objects.equals(reminder.getIdUser(), user.getId())){
            reminder.closeOneWork();
            reminderRepository.save(reminder);
        }

    }




}
