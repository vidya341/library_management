package com.example.librarymanagementsystem.ResponseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookResponseDto {
    private String title;

    private Boolean isAvailable;

    private Genre genre;

    private Date publicationDate;

    private Integer price;

    private String authorName;



}
