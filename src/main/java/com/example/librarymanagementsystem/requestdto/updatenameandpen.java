package com.example.librarymanagementsystem.requestdto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class updatenameandpen {
    private Integer authorId;
    private String newName;
    private String newPenName;
}
