package com.cts.mfrp.bkin.repo;

import com.cts.mfrp.bkin.model.LibraryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibraryEntryRepo extends JpaRepository<LibraryEntry, Long> {

    // This fetches the book data at the same time as the library entry in ONE query
    @Query("SELECT l FROM LibraryEntry l JOIN FETCH l.book WHERE l.user.id = :userId")
    List<LibraryEntry> findByUserId(@Param("userId") Long userId);
}