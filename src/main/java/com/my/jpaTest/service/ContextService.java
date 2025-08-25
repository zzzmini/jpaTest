package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContextService {
    @Autowired
    EntityManager em;

    // 저장하는 작업을 수행
    public Member memberInsert() {
        Member jang = Member.builder()
                .memberId("jang")
                .name("장원영")
                .build();
        // 영속성 공간에 저장하는 명령
        em.persist(jang);

        // member_id : jang 인 레코드를 찾아서 반환
        // Member 테이블에서 키 값이 jang 인 친구를 찾는 거.
        Member won = em.find(Member.class, "jang");
        return won;
    }
    public void transactionTest() {
        Member ahn = Member.builder()
                .memberId("jin")
                .name("안유진")
                .build();

        Member carina = Member.builder()
                .memberId("carina")
                .name("카리나")
                .build();

        em.persist(ahn);
        em.persist(carina);
        em.flush();
    }

    public void dirtyCheckingTest() {
        Member m = em.find(Member.class, "carina");
        m.setName("까리나에요");
    }

    public void deleteMember() {
        Member m = em.find(Member.class, "jin");
        em.remove(m);
    }
}
