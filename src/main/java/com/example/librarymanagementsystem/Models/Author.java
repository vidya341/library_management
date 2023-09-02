package com.example.librarymanagementsystem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_id;
    private String name;

    private String emailId;

    private Integer age;

    private String penName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> BookList = new ArrayList<>();

}
