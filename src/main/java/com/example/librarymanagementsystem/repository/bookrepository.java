package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bookrepository extends JpaRepository<Book,Integer> {
    List<Book> findBooksByGenre(Genre genre);

    List<Book> findBooksByIsAvailableFalse();
}
