package com.my.jpaTest.service;

import com.my.jpaTest.entity.Entertainment;
import com.my.jpaTest.entity.GirlGroup;
import com.my.jpaTest.entity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EnterService {
    @Autowired
    EntityManager em;

    public void initData() {
        // Enter 생성
        Entertainment startship = Entertainment.builder()
                .enterId("starship")
                .enterName("스타쉽")
                .build();

        Entertainment YG = Entertainment.builder()
                .enterId("YG")
                .enterName("와이지")
                .build();

        // Group 생성, 엔터 등록
        GirlGroup ive = GirlGroup.builder()
                .groupId("ive")
                .groupName("아이브")
                .entertainment(startship)
                .build();

        GirlGroup black = GirlGroup.builder()
                .groupId("blackPink")
                .groupName("블랙핑크")
                .entertainment(YG)
                .build();

        // Idol 생성, 그룹 등록
        IdolMember ahn = IdolMember.builder()
                .id("안유진")
                .name("유진")
                .girlGroup(ive)
                .build();
        IdolMember jang = IdolMember.builder()
                .id("장원영")
                .name("원영")
                .girlGroup(ive)
                .build();

        IdolMember jeni = IdolMember.builder()
                .id("제니")
                .name("째니")
                .girlGroup(black)
                .build();
        IdolMember jisu = IdolMember.builder()
                .id("지수")
                .name("지수다")
                .girlGroup(black)
                .build();

        // Enter에 Group List 등록
        startship.getGroups().add(ive);
        YG.getGroups().add(black);

        // Group에 Idol List 등록
        ive.getMembers().add(ahn);
        ive.getMembers().add(jang);
        black.getMembers().add(jeni);
        black.getMembers().add(jisu);

        // starShip 저장
        em.persist(startship);

        // YG 저장
        em.persist(YG);
    }
}
