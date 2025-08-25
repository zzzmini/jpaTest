package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ContextServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ContextService contextService;

    @Test
    @DisplayName("1차 캐시 테스트")
    void firstCash() {
        Member m = contextService.memberInsert();
        System.out.println("=======" + m);
    }

    @Test
    @DisplayName("데이터 영속성 보장 테스트")
    void 데이터_영속성_보장_테스트() {
        // 인스턴스를 생성 -> @Data -> EqualsAndHashCode로 동일
        Member a_1 = Member.builder()
                .memberId("hong")
                .name("홍길동")
                .build();
        Member b_1 = Member.builder()
                .memberId("hong")
                .name("홍길동")
                .build();
        System.out.println("위 : " + a_1.equals(b_1));
        // 엔티티매니저의 영속성 컨텍스트영역에서 가져와서 똑 같음.
        Member a = em.find(Member.class, "jang");
        Member b = em.find(Member.class, "jang");
        System.out.println("아래 : " + a.equals(b));
    }

    @Test
    @DisplayName("Transaction 쓰지지연 테스트")
    void transactionTest() {
        contextService.transactionTest();
    }

    @Test
    @DisplayName("Dirty Checking 테스트")
    // Dirty Checking : 변경감지
    void dirtyChecking() {
        contextService.dirtyCheckingTest();
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteMember() {
        contextService.deleteMember();
    }
}