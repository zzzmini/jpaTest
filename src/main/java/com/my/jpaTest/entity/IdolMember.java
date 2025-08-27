package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdolMember {
    @Id
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "groupId")
    private GirlGroup girlGroup;
}
