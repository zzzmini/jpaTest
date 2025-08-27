package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GirlGroup {
    @Id
    private String groupId;
    private String groupName;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "enterId")
    Entertainment entertainment;

    @Builder.Default
    @OneToMany(mappedBy = "girlGroup",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    List<IdolMember> members = new ArrayList<>();
}
