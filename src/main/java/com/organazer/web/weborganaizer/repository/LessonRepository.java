package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByIdUser(Long idUser);
    boolean existsByNameAndIdUser(String name, Long idUser);
}
