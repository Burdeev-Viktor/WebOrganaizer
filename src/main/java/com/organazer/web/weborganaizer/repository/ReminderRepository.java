package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {

   List<Reminder> findAllByIdUser(Long idUser);

    Reminder findReminderById(Long id);
    @Query(value = "SELECT * FROM`organizer_db`.`reminders` WHERE (id_user = ?1 AND need_work > 0)",nativeQuery = true)
    List<Reminder> getLabs(Long idUser);
    @Query(value = "SELECT * FROM`organizer_db`.`reminders` WHERE (id_user = ?1 AND need_work > 0 AND lesson = ?2 )",nativeQuery = true)
    Reminder getLabsByUserAndLesson(Long idUser,String lesson);
}
