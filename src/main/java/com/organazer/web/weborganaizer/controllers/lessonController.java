package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.Lesson;
import com.organazer.web.weborganaizer.model.LessonTimetable;
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
@RequestMapping("/lessons")
public class lessonController {
    private final TimetableService timetableService;
    private final UserService userService;
    private final LessonService lessonService;
    private int weekCount;

    public lessonController(TimetableService timetableService, UserService userService, LessonService lessonService) {
        this.timetableService = timetableService;
        this.userService = userService;
        this.lessonService = lessonService;
        weekCount = 0;
    }

    @GetMapping()
    public String getLessons(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByLogin(userDetails.getUsername());
        List<Lesson> lessonList = lessonService.findAllByIdUser(user.getId());
        LessonTimetable[][] timetables = timetableService.getSortLessonsTimetableOneWeek(timetableService.getLessonsWeekByNumber(weekCount, user.getId()));
        model.addAttribute("timetables",timetables);
        model.addAttribute("typeOfTest", Const.CHOICE_BOX_TYPE_OF_TEST);
        model.addAttribute("lessons",lessonList);
        model.addAttribute("newLesson",new Lesson());
        return "lessons";
    }
    @GetMapping("/+")
    public String nextWeek(){
        weekCount++;
        return "redirect:/lessons";
    }
    @GetMapping("/-")
    public String backWeek(){
        weekCount--;
        return "redirect:/lessons";
    }
    @PostMapping("/create-lesson")
    public String createLesson(@AuthenticationPrincipal UserDetails userDetails,Lesson lesson){
        lessonService.saveByUserDetails(lesson,userDetails);
        return "redirect:/lessons";
    }
    @PostMapping("/update-lesson/{id}")
    public String updateLesson(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long id,Lesson lesson){
        lesson.setId(id);
        lessonService.saveByUserDetails(lesson,userDetails);
        return "redirect:/lessons";
    }
    @GetMapping("/delete-lesson/{id}")
    public String deleteLesson(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id){
        System.out.println("Удаляю будильник " + id);
        lessonService.deleteByUserDetails(userDetails,id);
        return "redirect:/lessons";
    }
}
