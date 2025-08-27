package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class QueryServiceTest {
    @Autowired
    QueryService queryService;

    @Test
    @DisplayName("이만기를 ID로 찾기")
    void dynamicQuery() {
        List<Member> members = queryService.dynamicQuery();
        members.forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("팀 전체 가져오기")
    void findAllTeam() {
        List<Team> teamList = queryService.findAllTeam();
        teamList.forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("씨름팀 정보 가져오기")
    void fineMemberSsirum() {
        queryService.fineMemberSsirum()
                .forEach(x -> System.out.println(x));
    }
    @Test
    @DisplayName("씨름팀 인원 수 구하기")
    void countSsirum() {
        System.out.println("씨름팀 인원 수 : " +
                queryService.teamCount());
    }

    @Test
    @DisplayName("DTO로 받기")
    void getDto() {
        queryService.getMemberDto()
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("Projection으로 받기")
    void getProjection() {
        queryService.getProjection()
                .forEach(x-> System.out.println(x));
    }
}