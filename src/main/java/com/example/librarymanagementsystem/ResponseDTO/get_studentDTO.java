package com.example.librarymanagementsystem.ResponseDTO;

import com.example.librarymanagementsystem.Enum.Department;
import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class get_studentDTO {
    private String name;
    private Integer age;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Department dept;
    @Column(unique = true)
    private String email;

}
