package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Cardstatus;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.Enum.TransactionType;
import com.example.librarymanagementsystem.Models.Book;
import com.example.librarymanagementsystem.Models.Transaction;
import com.example.librarymanagementsystem.Models.lib_card;
import com.example.librarymanagementsystem.exceptions.BookNotAvailableException;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import com.example.librarymanagementsystem.repository.bookrepository;
import com.example.librarymanagementsystem.repository.cardrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Value("${book.maxLimit}")
    private Integer maxBookLimit;

    @Autowired
    private bookrepository bookRepository;

    @Autowired
    private cardrepository cardRepository;

    public static final Integer bookLimit = 6;


    public String issueBook(Integer bookId,Integer cardId)throws Exception{

        //Book Related Exception Handling

        Transaction transaction = new Transaction(TransactionStatus.PENDING, TransactionType.ISSUE,0);

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new Exception("Book Id is incorrect");
        }
        Book book = optionalBook.get();
        if(book.getIsAvailable()==Boolean.FALSE){
            throw new BookNotAvailableException("Book is not Avaialble");
        }


        //Card Related Exception Handling
        Optional<lib_card> optionalLibraryCard = cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new Exception("Card Id entered is Invalid");
        }

        lib_card card = optionalLibraryCard.get();
        if(!card.getStatus().equals(Cardstatus.Active)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction = transactionRepository.save(transaction);

            throw new Exception("Card is not in Right status");
        }
        if(card.getBooks_issued()>=bookLimit){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction = transactionRepository.save(transaction);
            throw new Exception("Already max Limit Books are issued");
        }
        /*  All the failed cases and invalid scenarios are over */
        //We have reached at a success point

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //update the card and book Entity
        book.setIsAvailable(Boolean.FALSE);
        card.setBooks_issued(card.getBooks_issued()+1);
        //We need to do unidirectional mappings :-->
        transaction.setBook(book);
        transaction.setLibraryCard(card);


        Transaction newTransactionWithId = transactionRepository.save(transaction);
        //We need to do in the parent classes
        book.getTransactionList().add(newTransactionWithId);
        card.getTransactionList().add(newTransactionWithId);

        bookRepository.save(book);
        cardRepository.save(card);

        //What all needs to saved
        return "Transaction has been saved successfully";

    }

    public String returnBook(Integer bookId,Integer cardId) throws Exception{
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new Exception("Book Id is incorrect");
        }
        Book book = optionalBook.get();



        //Card Related Exception Handling
        Optional<lib_card> optionalLibraryCard = cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new Exception("Card Id entered is Invalid");
        }

        lib_card card = optionalLibraryCard.get();




        List<Transaction> transactionList = transactionRepository.findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(book,card,TransactionStatus.SUCCESS,TransactionType.ISSUE);

        Transaction latestTransaction = transactionList.get(transactionList.size()-1);

        Date issueDate = latestTransaction.getCreatedAt();

        long milliSecondTime = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        long no_of_days_issued = TimeUnit.DAYS.convert(milliSecondTime,TimeUnit.MILLISECONDS);


        int fineAmount = 0;
        if(no_of_days_issued>15){
            fineAmount = (int) ((no_of_days_issued - 15)*5);
        }

        book.setIsAvailable(Boolean.TRUE);
        card.setBooks_issued(card.getBooks_issued()-1);

        Transaction transaction = new Transaction(TransactionStatus.SUCCESS,TransactionType.RETURN,fineAmount);

        transaction.setBook(book);
        transaction.setLibraryCard(card);


        Transaction newTransactionWithId = transactionRepository.save(transaction);

        book.getTransactionList().add(newTransactionWithId);
        card.getTransactionList().add(newTransactionWithId);

        //Saving the parents
        bookRepository.save(book);
        cardRepository.save(card);

        return "Book has successfully been returned";
    }
}
