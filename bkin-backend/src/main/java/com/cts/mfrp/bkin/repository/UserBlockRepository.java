package com.cts.mfrp.bkin.repository;

import com.cts.mfrp.bkin.entity.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {

    boolean existsByBlockerUsernameAndBlockedUsername(String blocker, String blocked);

    void deleteByBlockerUsernameAndBlockedUsername(String blocker, String blocked);

    // All usernames that `username` has blocked
    @Query("SELECT ub.blockedUsername FROM UserBlock ub WHERE ub.blockerUsername = :username")
    List<String> findBlockedByUser(@Param("username") String username);

    // All usernames that have blocked `username`
    @Query("SELECT ub.blockerUsername FROM UserBlock ub WHERE ub.blockedUsername = :username")
    List<String> findWhoBlockedUser(@Param("username") String username);
}
