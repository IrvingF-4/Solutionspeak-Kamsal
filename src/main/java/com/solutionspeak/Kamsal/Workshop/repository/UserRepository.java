package com.solutionspeak.Kamsal.Workshop.repository;

import com.solutionspeak.Kamsal.Workshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User getUserByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);
}