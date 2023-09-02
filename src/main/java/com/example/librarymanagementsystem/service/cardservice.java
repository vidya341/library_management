package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Models.lib_card;
import com.example.librarymanagementsystem.Models.student;
import com.example.librarymanagementsystem.repository.cardrepository;
import com.example.librarymanagementsystem.repository.studentrepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class cardservice {
    @Autowired
    private cardrepository card_repo;
    @Autowired
    private studentrepository stu_repo;


    public String add_card(lib_card card)throws Exception
    {
        if(card.getCard_id()!=null)
            throw new Exception("card id should not be a parameter");
        else
        {
            card_repo.save(card);
            return "card saved successfully";
        }

    }
    public String assign_card_student(int stu_id,int card_id)throws Exception
    {
        Optional<student> opt_stu = stu_repo.findById(stu_id);
        Optional<lib_card> opt_card = card_repo.findById(card_id);
        if(!opt_stu.isPresent())
        {
            throw new Exception("student with id is not present");
        }
        if(!opt_card.isPresent())
        {
            throw new Exception("card with card id is not present");
        }
        //to associate we need to set
        student stu = opt_stu.get();
        lib_card card = opt_card.get();
        card.setStu(stu);
        stu.setCard(card);
        stu_repo.save(stu);
        return "student has been assigned a card";
    }
}
