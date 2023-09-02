package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Models.Author;
import com.example.librarymanagementsystem.ResponseDTO.get_authorDTO;
import com.example.librarymanagementsystem.repository.authorrepository;
import com.example.librarymanagementsystem.requestdto.updatenameandpen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class authorservice {
    @Autowired
    private authorrepository au_repo;
    public String add_author(Author author)throws Exception
    {
        if(author.getAuthor_id()!=null)
        {
            throw new Exception("author id should not be a parameter");
        }
        else {
            au_repo.save(author);
            return "author saved successfully";
        }
    }
    public String updateNameAndPenName(updatenameandpen request)throws Exception{


        Optional<Author> authorOptional = au_repo.findById(request.getAuthorId());

        if(!authorOptional.isPresent()){
            throw new Exception("AuthorId is Invalid");
        }

        Author author = authorOptional.get();

        author.setName(request.getNewName());
        author.setPenName(request.getNewPenName());

        au_repo.save(author);

        return "Author Name and PenName has been updated";
    }

    public get_authorDTO getAuthorById(Integer authorId){

        Author author = au_repo.findById(authorId).get();
        get_authorDTO get_auth = new get_authorDTO(author.getName(),author.getEmailId(),author.getAge(),author.getPenName());
        return get_auth;

    }
}
