package com.organazer.web.weborganaizer.service;



import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.repository.ReminderRepository;
import com.organazer.web.weborganaizer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class ReminderService {
    private  final ReminderRepository reminderRepository;
    private  final UserRepository userRepository;

    public ReminderService(ReminderRepository reminderRepository, UserRepository userRepository) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
    }

    public Reminder findOneByIdAndUserId(Long userId,Long id){
        List<Reminder> reminderList = reminderRepository.findAllByIdUser(userId);
         return reminderList.stream()
                 .filter(element->Objects.equals(element.getId(),id))
                 .findFirst()
                 .orElse(null);
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

    public void closeOneWork(Long id,UserDetails userDetails){
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Reminder reminder = reminderRepository.findReminderById(id);
        if(Objects.equals(reminder.getIdUser(), user.getId())){
            reminder.closeOneWork();
            reminderRepository.save(reminder);
        }

    }




}
