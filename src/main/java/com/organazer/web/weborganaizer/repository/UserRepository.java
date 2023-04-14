package com.organazer.web.weborganaizer.repository;


import com.organazer.web.weborganaizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findUserByLogin(String login);
    boolean existsByLoginAndPassword(String login,String password);

}
