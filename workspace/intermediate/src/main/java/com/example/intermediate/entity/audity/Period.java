package com.example.intermediate.entity.audity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
/**
 * - @MappedSuperClass
 * 자바 진영에서는 상속관계 이지만, RDB진영에서는 상속관계가 아님을 표시할 때 사용.
 * 각 필드를 개별적으로 사용하거나 바로 접근해야 할 때 사용.
 * */
@MappedSuperclass
@Getter
public abstract class Period {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void created() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void updated() {
        this.updatedDate = LocalDateTime.now();
    }
}
