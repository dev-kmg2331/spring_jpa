package com.example.advanced.audit;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class Period {

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void create() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        this.updateDate = LocalDateTime.now();
    }
}
