package com.solutionspeak.Kamsal.Workshop.repository;

import com.solutionspeak.Kamsal.Workshop.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer>
        , JpaSpecificationExecutor<Workshop> {
}