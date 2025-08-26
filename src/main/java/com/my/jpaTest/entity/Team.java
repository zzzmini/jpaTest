package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Team {
    @Id
    private String teamId;
    private String teamName;
    // 팀에 소속된 멤버의 리스트를 저장
    // 연관관계 설정 : Member 클래스의 Team 정보를 가리킴
    @OneToMany(mappedBy = "team",
            fetch = FetchType.EAGER)
    @Builder.Default
    private List<Member>
            memberList = new ArrayList<>();
}
