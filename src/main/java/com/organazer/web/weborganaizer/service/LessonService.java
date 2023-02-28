package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.Const;
import com.organazer.web.weborganaizer.model.Lesson;
import com.organazer.web.weborganaizer.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonService {
    private final LessonRepository lessonsRepository;

    public LessonService(LessonRepository lessonsRepository) {
        this.lessonsRepository = lessonsRepository;
    }


    public List<Lesson> findAllByIdUser(Long id){
        return lessonsRepository.findAllByIdUser(id);
    }
    public String[] getAllLessonsNameByIdUser(Long id){
        List<Lesson> lessonList = findAllByIdUser(id);
        String[] names = new String[lessonList.size()];
        for (int i = 0; i < names.length; i++){
            names[i] = lessonList.get(i).getName();
        }
        return names;
    }
    public void save(Lesson lesson){
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

}
