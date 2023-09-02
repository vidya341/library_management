package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Models.lib_card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cardrepository extends JpaRepository<lib_card,Integer> {
}
