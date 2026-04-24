package com.cts.mfrp.bkin.repository;

import com.cts.mfrp.bkin.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m JOIN FETCH m.sender WHERE m.roomId = :roomId ORDER BY m.sentAt ASC")
    List<Message> findByRoomIdOrderBySentAtAsc(@Param("roomId") String roomId);

    Optional<Message> findTopByRoomIdOrderBySentAtDesc(String roomId);

    @Query("SELECT DISTINCT m.roomId FROM Message m WHERE m.roomId LIKE 'dm\\_%' ESCAPE '\\'")
    List<String> findAllDmRoomIds();
}
