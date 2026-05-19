package com.cts.mfrp.bkin.repository;

import com.cts.mfrp.bkin.entity.ChatDeletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatDeletionRepository extends JpaRepository<ChatDeletion, Long> {

    @Query("SELECT cd.roomId FROM ChatDeletion cd WHERE cd.username = :username")
    List<String> findRoomIdsByUsername(@Param("username") String username);

    Optional<ChatDeletion> findByUsernameAndRoomId(String username, String roomId);
}
