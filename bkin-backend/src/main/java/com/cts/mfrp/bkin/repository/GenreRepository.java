package com.cts.mfrp.bkin.repository;

import com.cts.mfrp.bkin.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Set<Genre> findByNameIn(Collection<String> names);
}
