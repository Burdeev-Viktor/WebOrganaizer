package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.Const;
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
        List<Lesson> lessonList = findAllByIdUser(id);
        return lessonList.stream()
                .map(Lesson::getName)
                .toArray(String[]::new);
    }
    private void save(Lesson lesson){
        lessonsRepository.save(lesson);
    }
    public boolean lessonIsExistsByNameAndIdUser(String name,Long id){
        return lessonsRepository.existsByNameAndIdUser(name,id);
    }
    public void delete(Lesson lesson){
        lessonsRepository.delete(lesson);
    }



    /**
     * Метод проверяет больше ли время1 или время2
     * @param time1 - время1
     * @param time2  - время2
     * @return
     */
    public static boolean time(String time1,String time2){
        String[] time1array = time1.split(Const.COLON);
        String[] time2array = time2.split(Const.COLON);
        boolean flag = ((Integer.parseInt(time1array[0])) >= (Integer.parseInt(time2array[0])));
        return flag;
    }

    public void saveByUserDetails(Lesson lesson, UserDetails userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Lesson lessonFordb;
        if(lesson.getId() != null){
            lessonFordb = lessonsRepository.getById(lesson.getId());
            if(Objects.equals(lessonFordb.getIdUser(), user.getId())){
                lesson.setIdUser(user.getId());
                lessonsRepository.save(lesson);
            }
        }else {
            lesson.setIdUser(user.getId());
            lessonsRepository.save(lesson);
        }

    }

    public void deleteByUserDetails(UserDetails userDetails, Long id) {
        User user = userRepository.findUserByLogin(userDetails.getUsername());
        Lesson lessonFordb = lessonsRepository.getById(id);
        if(Objects.equals(user.getId(), lessonFordb.getIdUser())){
            lessonsRepository.delete(lessonFordb);
        }
    }
}
