package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByIdUser(Long idUser);
    @Query(value = "SELECT name FROM `organizer_db`.`lessons` WHERE (id_user = ?1 OR id_user = 0)",nativeQuery = true)
    List<String> getLessonName(Long id_reminder);
    Lesson findLessonById(Long id);
}
