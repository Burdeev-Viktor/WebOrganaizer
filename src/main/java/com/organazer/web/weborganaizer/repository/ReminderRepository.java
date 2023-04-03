package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {
   List<Reminder> findAllBySwitchRAndIdUser(boolean switchR, Long idUser);
   List<Reminder> findAllByIdUser(Long idUser);

    Reminder findReminderById(Long id);
}
