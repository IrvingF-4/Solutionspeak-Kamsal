package com.solutionspeak.Kamsal.Workshop.repository;

import com.solutionspeak.Kamsal.Workshop.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer>, JpaSpecificationExecutor<Part> {
}