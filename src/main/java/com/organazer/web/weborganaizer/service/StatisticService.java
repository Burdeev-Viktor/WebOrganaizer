package com.organazer.web.weborganaizer.service;

import com.organazer.web.weborganaizer.model.Grade;
import com.organazer.web.weborganaizer.model.LessonTimetable;
import com.organazer.web.weborganaizer.model.Reminder;
import com.organazer.web.weborganaizer.model.statistics.GradeStatistic;
import com.organazer.web.weborganaizer.model.statistics.LabStatistic;
import com.organazer.web.weborganaizer.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    private final ReminderService reminderService;
    private static GradeRepository gradeRepository = null;

    public StatisticService(ReminderService reminderService, GradeRepository gradeRepository) {
        this.reminderService = reminderService;
        this.gradeRepository = gradeRepository;
    }

    public List<LabStatistic> getLabStatistic(Long idUser){
        return reminderService.getLabs(idUser).stream().map(LabStatistic::new).toList();
    }
    public List<GradeStatistic> getGradeStatistic(Long idUser){
        List<GradeStatistic> gradeStatistics = new ArrayList<>();
        List<Reminder> labs = reminderService.getLabs(idUser);
        List<List<Grade>> allGrades = labs.stream().map(lab -> {
            return gradeRepository.getGradesOfReminderId(lab.getId());
        }).toList();
        for (int i = 0; i < labs.size(); i++){
            gradeStatistics.add(new GradeStatistic(allGrades.get(i),labs.get(i).getLessonName()));
        }
        return gradeStatistics;
    }
    public static GradeStatistic gradeStatistic(Reminder reminder){
        return new GradeStatistic(gradeRepository.getGradesOfReminderId(reminder.getId()), reminder.getLessonName());
    }
}
