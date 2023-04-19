package com.example.advanced.entity.board;

import com.example.advanced.audit.Period;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity @Table(name = "TBL_REPLY")
@Getter @ToString @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period {
    @Id @GeneratedValue()
    @Column
    private Long id;

    @NotNull
    private String replContent;

    public void setReplContent(String replContent) {
        this.replContent = replContent;
    }

    public Reply(String replContent) {
        this.replContent = replContent;
    }
}
