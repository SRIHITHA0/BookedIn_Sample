package com.cts.mfrp.bkin.repo;

import com.cts.mfrp.bkin.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenres_NameIgnoreCase(String genreName);
    List<Book> findByAuthorIgnoreCase(String author);
}