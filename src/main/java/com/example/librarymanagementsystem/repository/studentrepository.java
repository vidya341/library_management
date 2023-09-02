package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Models.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentrepository extends JpaRepository<student,Integer> {

}
