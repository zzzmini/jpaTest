package com.my.jpaTest.quiz;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import com.my.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuizTest {
    @Autowired
    UsersRepository repository;

    @Test
    @Transactional
    @DisplayName("Given/When/Then으로 테스트 하기")
    void assertThatTest() {
        // 신규데이터 추가 테스트
        // Given
        Users jin = Users.builder()
                .name("안유진")
                .email("jin@korea.com")
                .gender(Gender.Female)
                .likeColor("Red")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        // When
        repository.save(jin);
        // Then
        // 이름으로 검색한 결과와 jin 이랑 같으면...성공
        Users result = repository.findByName("안유진").get(0);
        // 검사
        Assertions.assertThat(result.getEmail()).isEqualTo(jin.getEmail());
    }

    @Test
    @DisplayName("문제 1")
    void 문제1() {
        // feature/exam01 테스트
    }
}
