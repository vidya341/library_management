package com.example.librarymanagementsystem.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class get_authorDTO {
      private String name;
      private String emailId;
      private int age;
      private String penName;


}
