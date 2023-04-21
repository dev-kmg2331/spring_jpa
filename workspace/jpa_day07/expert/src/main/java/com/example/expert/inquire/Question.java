package com.example.expert.inquire;

import com.example.expert.audit.Period;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_QUESTION")
public class Question extends Period {
    @Id @GeneratedValue
    private Long id;
    private String questionTitle;
    private String questionContents;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Answer answer;
}
























