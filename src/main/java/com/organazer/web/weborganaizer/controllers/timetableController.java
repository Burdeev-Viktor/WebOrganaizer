package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.service.LessonService;
import com.organazer.web.weborganaizer.service.TimetableService;
import com.organazer.web.weborganaizer.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class timetableController {
    private final LessonService lessonService;
    private final TimetableService timetableService;
    private final UserService userService;

    public timetableController(LessonService lessonService, TimetableService timetableService, UserService userService) {
        this.lessonService = lessonService;
        this.timetableService = timetableService;
        this.userService = userService;
    }
    @GetMapping("")
    public String getTimetable(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByLogin(userDetails.getUsername());
        String[] nameLessons = lessonService.getAllLessonsNameByIdUser(user.getId());
        List<LessonTimetable> allLesson = timetableService.findAllByIdUser(user.getId());
        LessonTimetable[][][] lessons = timetableService.getSortLessonsTimetableAll( user.getId());
        model.addAttribute("lessons",lessons);
        model.addAttribute("allLessons",allLesson);
        model.addAttribute("typeLesson", Const.TYPE_OF_LESSON);
        model.addAttribute("dayOfWeek",Const.CHOICE_BOX_SIX_DAYS_OF_WEEK);
        model.addAttribute("numberOfWeek",Const.CHOICE_BOX_NUMBER_OF_WEEK);
        model.addAttribute("firstTime",Const.TIME_OF_START_LESSON_FIRST);
        model.addAttribute("secondTime",Const.TIME_OF_START_LESSON_SECOND);
        model.addAttribute("shiftEducation",Const.CHOICE_BOX_SHIFT_EDUCATION);
        model.addAttribute("newLessonTimetable",new LessonTimetable());
        model.addAttribute("updateLesson",new LessonTimetable());
        model.addAttribute("nameLessons",nameLessons);
        model.addAttribute("reminder",new Reminder());
        return "timetable";
    }
    @PostMapping("/create-lesson-timetable")
    public  String createLessonTimetable(LessonTimetable lessonTimetable,@AuthenticationPrincipal UserDetails userDetails){
        timetableService.saveByUserDetails(lessonTimetable,userDetails);
        return "redirect:/timetable";
    }
    @PostMapping("/update-lesson-timetable/{id}")
    public String updateLessonTimetable( LessonTimetable lessonTimetable,@AuthenticationPrincipal UserDetails userDetails){
        timetableService.saveByUserDetails(lessonTimetable,userDetails);
        return "redirect:/timetable";
    }
    @GetMapping("/delete-lesson/{id}")
    public String deleteLesson(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails){
        timetableService.deleteByIdAndUserDetails(id,userDetails);
        return "redirect:/timetable";
    }
}
