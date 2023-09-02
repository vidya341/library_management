package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Department;
import com.example.librarymanagementsystem.Models.student;
import com.example.librarymanagementsystem.ResponseDTO.get_studentDTO;
import com.example.librarymanagementsystem.repository.studentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class studentservice {
    @Autowired
    private studentrepository stu_repo;
    //add student
    public String add_student(student stu)throws Exception
    {
        if(stu.getRoll_no()!=null)
            throw new Exception("ID should not be a parameter");
        else {
            stu_repo.save(stu);
            return "student added successfully";

        }


    }
    public Department get_dept_by_id(int stu_id)throws Exception
    {
        Optional<student> opt_stu = stu_repo.findById(stu_id);
        if(!opt_stu.isPresent())
            throw new Exception("student is not present ,please provide proper id");
        else {
            student stu = opt_stu.get();
            return stu.getDept();
        }

    }
    public get_studentDTO get_student_by_id(int id) throws Exception{
        Optional<student> opt_stu = stu_repo.findById(id);
        if(!opt_stu.isPresent())
            throw new Exception("id is invalid");
        else {
            student stu = opt_stu.get();
            get_studentDTO student_by_id = new get_studentDTO(stu.getName(),stu.getAge(),stu.getGender(),stu.getDept(),stu.getEmail());
            return student_by_id;
        }
    }
}
