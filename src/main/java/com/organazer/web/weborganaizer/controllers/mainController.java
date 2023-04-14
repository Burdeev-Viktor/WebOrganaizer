package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.service.LessonService;
import com.organazer.web.weborganaizer.service.ReminderService;
import com.organazer.web.weborganaizer.service.TimetableService;
import com.organazer.web.weborganaizer.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class mainController {
    private final LessonService lessonService;
    private final TimetableService timetableService;
    private final UserService userService;
    private final ReminderService reminderService;
    private int weekCount;

    public mainController(LessonService lessonService, TimetableService timetableService, UserService userService, ReminderService reminderService) {
        this.lessonService = lessonService;
        this.timetableService = timetableService;
        this.userService = userService;
        this.reminderService = reminderService;
        weekCount = 0;
    }

    @GetMapping("/")
    public String getMain(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByLogin(userDetails.getUsername());
        LessonTimetable[][] timetables = timetableService.getSortLessonsTimetableOneWeek(timetableService.getLessonsWeekByNumber(weekCount, user.getId()));
        List<Reminder> reminderList = reminderService.findAllByIdUser(user.getId());
        String[] nameLessons = lessonService.getAllLessonsNameByIdUser(user.getId());
        model.addAttribute("lessons",timetables);
        model.addAttribute("reminders",reminderList);
        model.addAttribute("reminder",new Reminder());
        model.addAttribute("nameLessons",nameLessons);
        model.addAttribute("days", Const.CHOICE_BOX_SEVEN_DAYS_OF_WEEK);
        model.addAttribute("switch",Const.CHOICE_BOX_SETTING_SWITCH);
        return "main";
    }
    @GetMapping("/+")
    public String nextWeek(){
        weekCount++;
        return "redirect:/";
    }
    @GetMapping("/-")
    public String backWeek(){
        weekCount--;
        return "redirect:/";
    }

    @PostMapping("/create-reminder")
    public String postQuest(@AuthenticationPrincipal UserDetails userDetails,Reminder reminder){
        User user = userService.findUserByLogin(userDetails.getUsername());
        reminder.setIdUser(user.getId());
        reminderService.save(reminder);
        return "redirect:/";
    }


}
