package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface authorrepository extends JpaRepository<Author,Integer> {
    @Query(value = "select * from author where age >=:enteredAge;",nativeQuery = true)
    List<Author> findAuthorsGreaterThanAge(Integer enteredAge);
}
