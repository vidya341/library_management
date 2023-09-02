package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.ResponseDTO.BookResponseDto;
import com.example.librarymanagementsystem.requestdto.addbookrequest;
import com.example.librarymanagementsystem.service.bookservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class bookcontroller {
    @Autowired
    private bookservice book_ser;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody addbookrequest addBookRequestDto){

        try{

            String result = book_ser.addBook(addBookRequestDto);
            return new ResponseEntity(result, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getByGenre")
    public ResponseEntity getBookListByGenre(@RequestParam("genre") Genre genre){
        List<BookResponseDto> responseDtoList = book_ser.getBookListByGenre(genre);
        return new ResponseEntity(responseDtoList, HttpStatus.OK);
    }

}
