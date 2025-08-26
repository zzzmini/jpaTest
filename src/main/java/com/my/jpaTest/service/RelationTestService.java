package com.my.jpaTest.service;

import com.my.jpaTest.entity.Child;
import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Parent;
import com.my.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RelationTestService {
    @Autowired
    EntityManager em;

    // 팀과 멤버를 저장하는 코드
    public void insertMemberAndTeam() {
        Team team = Team.builder()
                .teamId("ive")
                .teamName("아이브")
                .build();
        em.persist(team);

        Member member = Member.builder()
                .memberId("jang")
                .name("장원영")
                .team(team)
                .build();
        em.persist(member);
    }

    public void insertBothRelation() {
        // 씨름팀
        Team ssirum = Team.builder()
                .teamId("ssirum")
                .teamName("씨름팀")
                .build();
        em.persist(ssirum);

        // 이만기
        Member lee = Member.builder()
                .memberId("lee")
                .name("이만기")
                .team(ssirum)
                .build();
        em.persist(lee);
        // 강호동
        Member kang = Member.builder()
                .memberId("kang")
                .name("강호동")
                .team(ssirum)
                .build();
        em.persist(kang);

        // 씨름팀에 이만기와 강호동을 리스트에 추가
        // 객체지향 개념을 확실하게 하기 위해
        ssirum.getMemberList().add(lee);
        ssirum.getMemberList().add(kang);
    }

    public void saveChildren() {
        // 자식 생성
        Child c1 = new Child();
        Child c2 = new Child();

        // 부모 생성
        Parent p = new Parent();
        // 자식에 부모 할당
        c1.setParent(p);
        c2.setParent(p);
        // 부모의 자식 리스트만들기
        p.getChildren().add(c1);
        p.getChildren().add(c2);
        // 부모를 저장 -> 자식들도 따라 저장됨
        // Cascade.Persist 로 설정 되어 있기 때문
        em.persist(p);
    }

    public void deleteParent() {
        Parent p = em.find(Parent.class, 1L);
        em.remove(p);
    }
}
