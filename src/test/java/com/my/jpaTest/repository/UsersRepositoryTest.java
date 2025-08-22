package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByCreatedAtAfter Test")
    void findByCreatedAtAfter() {
        LocalDate yesterday = LocalDate.now().minusDays(2L);
        System.out.println(yesterday);
        LocalDateTime start = yesterday.atTime(23, 59, 59);
        usersRepository
                .findByCreatedAtAfter(start)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("최근 한 달 자료 찾기(오늘 포함)")
    void findByCreateAtBetween() {
        // 한달 이전의 기준일 설정
        LocalDate baseDate = LocalDate.now().minusMonths(1L);
        // 한달 전 날에다 시분초를 붙인다.
        LocalDateTime start = baseDate.atTime(0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        usersRepository.findByCreatedAtBetween(start, end)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("여러가지 좋아하는 색상 검색하기")
    void findByLikeColorIn() {
        // 검색하고자 하는 색상의 리스트 만들기
        usersRepository.findByLikeColorIn(Lists.newArrayList("Red", "Pink"))
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("ID가 91 이상인 자료")
    void findByIdGreaterThanEqual() {
        usersRepository.findByIdGreaterThanEqual(91L)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByNameStartingWith")
    void findByNameStartingWith() {
        usersRepository.findByNameStartingWith("D")
                .forEach(x -> System.out.println(x));
    }

    // contains와 like 만 테스트
    @Test
    @DisplayName("findByEmailContains")
    void findByEmailContains() {
        usersRepository.findByEmailContains("org")
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findByEmailLike")
    void findByEmailLike() {
        usersRepository.findByEmailLike("%org%")
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findByIdBetweenOrderByNameDesc")
    void findByIdBetweenOrderByNameDesc() {
        usersRepository.findByIdBetweenOrderByNameDesc(1L, 10L)
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findTop10ByLikeColorOrderByGenderAscOrderByCreatedAtDesc")
    void findTop10ByLikeColorOrderByGenderAscCreatedAtDesc() {
        usersRepository.findTop10ByLikeColorOrderByGenderAscCreatedAtDesc("Orange")
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("findByLikeColor")
    void findByLikeColor() {
        usersRepository.findByLikeColor("Orange",
                Sort.by(Sort.Order.asc("gender"),
                        Sort.Order.desc("createdAt")))
                .forEach(x-> System.out.println(x));
    }
}