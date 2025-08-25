package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    private String memberId;
    private String name;
}
