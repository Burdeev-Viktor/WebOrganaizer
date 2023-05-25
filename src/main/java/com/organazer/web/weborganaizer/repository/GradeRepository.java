package com.organazer.web.weborganaizer.repository;

import com.organazer.web.weborganaizer.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    @Query(value = "SELECT * FROM `organizer_db`.`grades` WHERE (id_reminder = ?1)",nativeQuery = true)
    List<Grade> getGradesOfReminderId(Long id_reminder);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO grades ( id_reminder, grade) VALUES (?1, ?2)",nativeQuery = true)
    void addGrade(Long id,short grade);

}