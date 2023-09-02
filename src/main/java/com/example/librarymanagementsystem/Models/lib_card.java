package com.example.librarymanagementsystem.Models;

import com.example.librarymanagementsystem.Enum.Cardstatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class lib_card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_id;
    @Enumerated(value = EnumType.STRING)
    private Cardstatus status;
    private int books_issued;
    @OneToOne
    @JoinColumn
    private student stu;
    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
