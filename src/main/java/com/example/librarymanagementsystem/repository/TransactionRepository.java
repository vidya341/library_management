package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.Enum.TransactionType;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.Models.Transaction;
import com.example.librarymanagementsystem.Models.lib_card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book, lib_card card, TransactionStatus transactionStatus, TransactionType transactionType);
}
