package com.example.librarymanagementsystem.Models;


import com.example.librarymanagementsystem.Enum.Department;
import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roll_no;
    private String name;
    private Integer age;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Department dept;
    @Column(unique = true)
    private String email;

    @OneToOne(mappedBy = "stu", cascade = CascadeType.ALL)
    private lib_card card;


}
