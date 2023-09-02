package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Department;
import com.example.librarymanagementsystem.Models.student;
import com.example.librarymanagementsystem.ResponseDTO.get_studentDTO;
import com.example.librarymanagementsystem.service.studentservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class studentcontroller {
    @Autowired
    private studentservice stu_ser;

    //add student model
    @PostMapping("/addstudent")
    public ResponseEntity add_student(@RequestBody student stu)
    {
        try{
            String res = stu_ser.add_student(stu);
            return new ResponseEntity(res, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            log.error("student not added {}"+e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/get_dept_by_id")
    public ResponseEntity get_dept_by_id(@RequestParam("id") Integer id)
    {
        try {
            Department res = stu_ser.get_dept_by_id(id);
            return new ResponseEntity(res,HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error("student not found {}"+e.getMessage());
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }

    }
    //get student details by id
    @GetMapping("/studentbyid")
    public get_studentDTO get_student_by_id(@RequestParam("id") Integer id)
    {
        try{
            return stu_ser.get_student_by_id(id);
        }
        catch (Exception e)
        {
            log.error("id is invalid{}"+e.getMessage());
            return null;
        }
    }

}
