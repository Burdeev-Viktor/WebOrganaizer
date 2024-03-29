package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.*;
import com.organazer.web.weborganaizer.model.enums.DayOfWeek;
import com.organazer.web.weborganaizer.model.enums.NumberWeek;
import com.organazer.web.weborganaizer.model.enums.TypeOfLesson;
import com.organazer.web.weborganaizer.service.LessonService;
import com.organazer.web.weborganaizer.service.TimetableService;
import com.organazer.web.weborganaizer.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private final LessonService lessonService;
    private final TimetableService timetableService;
    private final UserService userService;

    public TimetableController(LessonService lessonService, TimetableService timetableService, UserService userService) {
        this.lessonService = lessonService;
        this.timetableService = timetableService;
        this.userService = userService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getTimetable(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByLogin(userDetails.getUsername());
        String[] nameLessons = lessonService.getAllLessonsNameByIdUser(user.getId());
        List<LessonTimetable> allLesson = timetableService.findAllByIdUser(user.getId());
        LessonTimetable[][][] lessons = timetableService.getSortLessonsTimetableAll( user.getId());
        model.addAttribute("lessons",lessons);
        model.addAttribute("typeLesson", TypeOfLesson.values());
        model.addAttribute("allLessons",allLesson);
        model.addAttribute("dayOfWeek", DayOfWeek.getSixDay());
        model.addAttribute("numberOfWeek", NumberWeek.values());
        model.addAttribute("firstTime",Const.TIME_OF_START_LESSON_FIRST);
        model.addAttribute("secondTime",Const.TIME_OF_START_LESSON_SECOND);
        model.addAttribute("shiftEducation",Const.CHOICE_BOX_SHIFT_EDUCATION);
        model.addAttribute("newLessonTimetable",new LessonTimetable());
        model.addAttribute("updateLesson",new LessonTimetable());
        model.addAttribute("nameLessons",nameLessons);
        model.addAttribute("reminder",new Reminder());
        return "timetable";
    }
    @RequestMapping( method = RequestMethod.POST)
    public  String createLessonTimetable(LessonTimetable lessonTimetable,@AuthenticationPrincipal UserDetails userDetails){
        timetableService.saveByUserDetails(lessonTimetable,userDetails);
        return "redirect:/timetable";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateLessonTimetable( LessonTimetable lessonTimetable,@AuthenticationPrincipal UserDetails userDetails){
        timetableService.saveByUserDetails(lessonTimetable,userDetails);
        return "redirect:/timetable";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteLesson(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails){
        timetableService.deleteByIdAndUserDetails(id,userDetails);
        return "redirect:/timetable";
    }
}
