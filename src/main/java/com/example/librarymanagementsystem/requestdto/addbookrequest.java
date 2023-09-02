package com.example.librarymanagementsystem.requestdto;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.Models.Author;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.Date;

@Data

public class addbookrequest {
    private String title;
    private Boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Date publicationDate;
    private Integer price;
    private int author_id;
}
