package com.example.librarymanagementsystem.Models;


import com.example.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private int book_id;
    private String title;
    private Boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Date publicationDate;
    private Integer price;

    @ManyToOne
    @JoinColumn
    private Author author;


    public Book(String title, boolean isAvailable, Genre genre, Date publicationDate, Integer price) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.price = price;
    }
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
