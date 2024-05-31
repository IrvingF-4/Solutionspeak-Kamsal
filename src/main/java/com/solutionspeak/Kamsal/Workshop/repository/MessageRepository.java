package com.solutionspeak.Kamsal.Workshop.repository;

import com.solutionspeak.Kamsal.Workshop.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {
    List<Message> findBySenderIdAndRecipientId(int senderId, int recipientId);
}