package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class transaction {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId")Integer bookId, @RequestParam("cardId")Integer cardId){

        try{
            String result = transactionService.issueBook(bookId,cardId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/returnBook")
    public ResponseEntity return_book(@RequestParam("bookId")Integer bookId,@RequestParam("cardId")Integer cardId)
    {
        try {
            String ans = transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(ans,HttpStatus.OK);

        }
        catch (Exception e)
        {
            log.error("invalid details{}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
