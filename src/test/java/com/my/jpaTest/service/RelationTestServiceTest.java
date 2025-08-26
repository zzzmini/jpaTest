package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Parent;
import com.my.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RelationTestServiceTest {
    @Autowired
    RelationTestService relationTestService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("MemberAndTeam Insert Test")
    void insertTest() {
        relationTestService.insertMemberAndTeam();
    }

    @Test
    @DisplayName("단방향 연관관계 설정 후 팀 이름찾기")
    void findTeamName() {
        // jang의 정보를 가져온다.
        Member jang = em.find(Member.class, "jang");
        // 팀정보 출력
        System.out.println("팀이름 : " + jang.getTeam().getTeamName());
    }


    @Test
    @DisplayName("양방향 연관관계 설정 후 저장하기")
    void saveBothData() {
        relationTestService.insertBothRelation();
    }

    @Test
    @DisplayName("씨름팀 멤버출력하기")
    void 씨름팀멤버출력() {
        Team team = em.find(Team.class, "ssirum");
        List<Member> members = team.getMemberList();
        for (Member m : members) {
            System.out.println(m.getName());
        }
    }

    @Test
    @DisplayName("영속성전이 테스트")
    void persistTest() {
        relationTestService.saveChildren();
    }

    @Test
    @DisplayName("부모삭제테스트-자식도같이")
    void deleteParent() {
        relationTestService.deleteParent();
    }
    // 장원영(key : jang)의 팀 이름을 알고 싶어요
//    @Test
//    @DisplayName("ID:jang의 팀 이름 찾기 테스트")
//    void findTeamName() {
//        // 연관관계가 없으면 한 없이 찾아들어가서 원하는 자료를 찾아야 함.
//        // 1. member 에서  jang을 찾아온다.
//        Member jang = em.find(Member.class, "jang");
//        // 2. member의 teamId로 team에서 검색한다.
//        Team team = em.find(Team.class, jang.getTeamId());
//        System.out.println("Team Name : " + team.getTeamName());
//    }
}