package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("1. findByName Test")
    void findByNameTest() {
        String name = "Basil Steketee";
        usersRepository
                .findByName(name)
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("pink 색상 상위 3개 찾기 테스트")
    void findByColor() {
        String color = "Pink";
        usersRepository
                .findTop3ByLikeColor(color)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByGenderAndLikeColor_Test")
    void findByGenderAndLikeColor() {
        usersRepository.findByGenderAndLikeColor(Gender.Female, "Red")
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findByCreatedAtAfter Test")
    void findByCreatedAtAfter() {
        usersRepository
                .findByCreatedAtAfter(LocalDateTime.now().minusDays(1L))
                .forEach(x -> System.out.println(x));
    }
}