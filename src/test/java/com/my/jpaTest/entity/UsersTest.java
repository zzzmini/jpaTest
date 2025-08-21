package com.my.jpaTest.entity;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersTest {
    @Autowired
    UsersRepository repository;

    @Test
    @DisplayName("새로운 User 입력하기 테스트")
    void userInputTest() {
        // 현재의 테이블 레코드 수를 출력
        System.out.println("데이터 추가 이전 : " + repository.count());
        // 빌더를 이용한 클래스 생성
        Users users = Users.builder()
                .name("장원영")
                .email("jang@ai.net")
                .gender(Gender.Female)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .likeColor("Pink")
                .build();
        repository.save(users);

        // 입력 후 레코드 수 출력
        System.out.println("데이터 추가 이후 : " + repository.count());
    }

    @Test
    @DisplayName("전체 레코드 수 출력과 id = 2L인 데이터 존재 확인")
    void userCountAndExistTest() {
        // 전체 레코드 수 출력
        Long count = repository.count();
        System.out.println("전체 레코드 수 : " + count);
        // 2L 존재하는 지 확인
        boolean exist = repository.existsById(2L);
        System.out.println("2번 레코드 존재 확인 : " + exist);
    }

    @Test
    @DisplayName("id = 1L 삭제 후 존재 확인하기")
    @Transactional
    void userDeleteAndExistTest() {
        repository.deleteById(1L);
        boolean exist = repository.existsById(1L);
        System.out.println("1번 레코드 존재 확인 : " + exist);
    }
}