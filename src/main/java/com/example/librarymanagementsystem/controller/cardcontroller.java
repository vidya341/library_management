package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Models.lib_card;
import com.example.librarymanagementsystem.service.cardservice;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@Slf4j


public class cardcontroller {
    @Autowired
    private cardservice card_ser;

    @PostMapping("/add_card")
    public ResponseEntity add_card(@RequestBody lib_card card)
    {
        try{
            String res = card_ser.add_card(card);
            return new ResponseEntity<>(res, HttpStatus.OK);

        }
        catch (Exception e)
        {
            log.error("card not added{}"+e.getMessage());
            return new ResponseEntity<>("card not saved",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/assign_card_to_student")
    public ResponseEntity assign_card_student(@RequestParam("stu_id")Integer stu_id,@RequestParam("card_id")Integer card_id)
    {
        try {
            String res = card_ser.assign_card_student(stu_id,card_id);
            return new ResponseEntity(res,HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error("either student or card id is not valid"+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


}
