package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.LessonTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<LessonTimetable,Long> {
    List<LessonTimetable> findAllByIdUser(Long idUser);

    @Query( value = "SELECT * FROM timetable u WHERE u.id_user = ?1 AND u.number_of_week = ?2 OR u.number_of_week = 'Каждую' " ,
    nativeQuery = true)
    List<LessonTimetable> findAllByIdUserAndNumberOfWeek(Long id,String week);


}
