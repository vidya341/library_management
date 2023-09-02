package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.ResponseDTO.get_authorDTO;
import com.example.librarymanagementsystem.requestdto.updatenameandpen;
import com.example.librarymanagementsystem.service.authorservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class authorcontroller {
    @Autowired
    private authorservice authorService;
    @PostMapping("/add_author")
    public ResponseEntity add_author(@RequestBody Author author)
    {
        try {
            String res = authorService.add_author(author);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error("author id should not be provided",e.getMessage());
            return new ResponseEntity<>("author id should not be specified",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateNameAndPenName")
    public String updateAuthorNameAndPenName(@RequestBody updatenameandpen updateNameAndPenNameRequest){


        //@RequestBody Author author

        //1 endpoint has become long
        //Exposed in the URL itself

        try{
            String result = authorService.updateNameAndPenName(updateNameAndPenNameRequest);
            return result;

        }catch (Exception e){
            return "Author Id is invalid"+e.getMessage();
        }

    }
    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam("authorId")Integer authorId){
        get_authorDTO auth = authorService.getAuthorById(authorId);
        return new ResponseEntity(auth,HttpStatus.OK);


    }
}
