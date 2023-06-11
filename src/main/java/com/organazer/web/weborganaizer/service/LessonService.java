package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.model.Lesson;
import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.repository.LessonRepository;
import com.organazer.web.weborganaizer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LessonService {
    private final LessonRepository lessonsRepository;
    private final UserRepository userRepository;

    public LessonService(LessonRepository lessonsRepository, UserRepository userRepository) {
        this.lessonsRepository = lessonsRepository;
        this.userRepository = userRepository;
    }


    public List<Lesson> findAllByIdUser(Long id){
        return lessonsRepository.findAllByIdUser(id);
    }
    public String[] getAllLessonsNameByIdUser(Long id){
        List<String> lessonList = lessonsRepository.getLessonName(id);
        return lessonList.toArray(String[]::new);
    }
    private void save(Lesson lesson){
        lessonsRepository.save(lesson);
    }

    public void delete(Lesson lesson){
        lessonsRepository.delete(lesson);
    }


    public void saveByUserDetails(Lesson lesson, UserDetails userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Lesson lessonFor_db;
        if(lesson.getId() != null){
            lessonFor_db = lessonsRepository.getById(lesson.getId());
            if(Objects.equals(lessonFor_db.getIdUser(), user.getId())){
                lesson.setIdUser(user.getId());
                save(lesson);
            }
        }else {
            lesson.setIdUser(user.getId());
            lessonsRepository.save(lesson);
        }

    }

    public void deleteByUserDetails(UserDetails userDetails, Long id) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Lesson lessonFor_db = lessonsRepository.findLessonById(id);
        if(Objects.equals(user.getId(), lessonFor_db.getIdUser())){
            lessonsRepository.delete(lessonFor_db);
        }
    }
}
