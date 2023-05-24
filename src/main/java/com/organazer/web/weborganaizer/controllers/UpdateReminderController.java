package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.model.*;
import com.organazer.web.weborganaizer.model.enums.DayOfWeek;
import com.organazer.web.weborganaizer.model.enums.SettingSwitch;
import com.organazer.web.weborganaizer.model.enums.TypeOfLesson;
import com.organazer.web.weborganaizer.service.LessonService;
import com.organazer.web.weborganaizer.service.ReminderService;
import com.organazer.web.weborganaizer.service.TimetableService;
import com.organazer.web.weborganaizer.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/reminder-update")
public class UpdateReminderController {
    private final LessonService lessonService;
    private final TimetableService timetableService;
    private final UserService userService;
    private final ReminderService reminderService;

    public UpdateReminderController(LessonService lessonService, TimetableService timetableService, UserService userService, ReminderService reminderService) {
        this.lessonService = lessonService;
        this.timetableService = timetableService;
        this.userService = userService;
        this.reminderService = reminderService;
    }
    @RequestMapping(value = "/set-week-count/{id}",method = RequestMethod.GET)
    public String weekCount(@PathVariable("id") Long id,HttpSession session){
        session.setAttribute("weekCount",0);
        return "redirect:/reminder-update/" + id;
    }
    @RequestMapping(value = "/nextW/{id}",method = RequestMethod.GET)
    public String nextWeek(@PathVariable("id") Long id,HttpSession session){
        session.setAttribute("weekCount",((int)session.getAttribute("weekCount")) + 1);
        return "redirect:/reminder-update/" + id;
    }
    @RequestMapping(value = "/backW/{id}",method = RequestMethod.GET)
    public String backWeek(@PathVariable("id") Long id,HttpSession session){
        session.setAttribute("weekCount",((int)session.getAttribute("weekCount")) - 1);
        return "redirect:/reminder-update/" + id;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String reminderUpdateForm(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model,HttpSession session) {
        User user = userService.findUserByLogin(userDetails.getUsername());
        Reminder reminder = reminderService.findOneByIdAndUserId(user.getId(),id);
        LessonTimetable[][] timetables = timetableService.getSortLessonsTimetableOneWeek(timetableService.getLessonsWeekByNumber((int)session.getAttribute("weekCount"), user.getId()));
        List<Reminder> reminderList = reminderService.findAllByIdUser(user.getId());
        String[] nameLessons = lessonService.getAllLessonsNameByIdUser(user.getId());
        model.addAttribute("updateReminder",reminder);
        model.addAttribute("typeLesson", TypeOfLesson.values());
        model.addAttribute("lessons",timetables);
        model.addAttribute("reminders",reminderList);
        model.addAttribute("nameLessons",nameLessons);
        model.addAttribute("days", DayOfWeek.values());
        model.addAttribute("switch", SettingSwitch.values());
        return "reminder-update";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateReminder(Reminder reminder){
        reminderService.save(reminder);
        return "redirect:/";
    }
    @RequestMapping (value = "/{id}",method = RequestMethod.DELETE)
    public String removeReminder(@AuthenticationPrincipal UserDetails userDetails,@PathVariable("id") Long id){
        reminderService.deleteByIdForUserDetails(id,userDetails);
        return "redirect:/";
    }
    @RequestMapping(value = "/close-work/{id}",method = RequestMethod.PUT)
    public String closeOneWork(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long id){
        reminderService.closeOneWork(id,userDetails);
        return "redirect:/reminder-update/" + id;
    }

}
