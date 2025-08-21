package com.my.jpaTest.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    // 새로운 행이 추가될 때 만 입력일이 자동 추가
    @CreatedDate
    LocalDateTime insertedAt;
    // 데이터가 수정될 때 자동으로 수정일이 기록
    @LastModifiedDate
    LocalDateTime updatedAt;
}
