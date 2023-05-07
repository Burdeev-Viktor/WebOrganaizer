package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByIdUser(Long idUser);
    boolean existsByNameAndIdUser(String name, Long idUser);
//    @Query("select * from lessons l where l.id = :id")
//    List<Lesson> findByIdFetchParent(@Param("id") Long id);
    Lesson findLessonById(Long id);
}
