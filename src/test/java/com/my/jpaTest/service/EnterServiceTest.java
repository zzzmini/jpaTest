package com.my.jpaTest.service;

import com.my.jpaTest.entity.GirlGroup;
import com.my.jpaTest.entity.IdolMember;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EnterServiceTest {
    @Autowired
    EnterService enterService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("데이터 입력 테스트")
    void dataInputTest() {
        enterService.initData();
    }

    // 1. 지수가 속한 걸그룹 이름과 엔터테인트 회사 이름 출력하기.
    @Test
    @DisplayName("문제 1")
    void 문제1() {
        IdolMember jisu = em.find(IdolMember.class, "지수");
        String groupName = jisu.getGirlGroup().getGroupName();
        String enterName = jisu.getGirlGroup().getEntertainment().getEnterName();
        System.out.println("Group : " + groupName + ", Enter : " + enterName);
    }

    // 2. blackPink 멤버 리스트 출력하기.
    @Test
    @DisplayName("문제 2")
    void 문제2() {
        GirlGroup group = em.find(GirlGroup.class, "blackPink");
        group.getMembers().forEach(
                x-> System.out.println(x.getName())
        );
    }
}