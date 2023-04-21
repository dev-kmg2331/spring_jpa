package com.example.advanced.entity.member;

import com.example.advanced.audit.Period;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class File extends Period {
    @Id @GeneratedValue
    private Long id;

    private String fileName;
    private String filePath;
    private String fileUuid;
    private Long fileSize;
}



















