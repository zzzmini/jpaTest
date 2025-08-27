package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entertainment {
    @Id
    private String enterId;
    private String enterName;
    @OneToMany(mappedBy = "entertainment",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    @Builder.Default
    List<GirlGroup> groups = new ArrayList<>();
}
