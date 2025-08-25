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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    void findByGenderAndNameContainsOrGenderAndNameContains() {
        repository
                .findByGenderAndNameContainsOrGenderAndNameContains(
                        Gender.Female, "w", Gender.Female, "m")
                .forEach(x -> System.out.println(x));
        // 다시 마지막
    }

    @Test
    @DisplayName("문제 2")
    void net을포함하는데이터건수() {
        System.out.println(
                repository.findByEmailContains("net")
                        .stream().count()
        );
    }

    @Test
    @DisplayName("문제 3")
    void findByUpdatedAtGreaterThanEqualAndNameLike() {
        LocalDate baseDate = LocalDate.now()
                .minusMonths(1L)
                .plusDays(1L);
        LocalDateTime start = baseDate.atStartOfDay();
        repository.findByUpdatedAtGreaterThanEqualAndNameLike(
                start, "J%"
        ).forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 4")
        // ID, 이름, 성별, 생성일
    void findTop10ByOrderByCreatedAtDesc() {
        List<Users> results = repository.findTop10ByOrderByCreatedAtDesc();
        for (Users user : results) {
            System.out.println("ID : " + user.getId() +
                    ", Name : " + user.getName() +
                    ", Gender : " + user.getGender() +
                    ", CreatedAt : " + user.getCreatedAt());
        }
    }

    @Test
    @DisplayName("문제 5")
        // 사이트를 제외한 계정만 출력
    void findByGenderAndLikeColor() {
        List<Users> results = repository.findByGenderAndLikeColor(
                Gender.Male, "Red"
        );
        for (Users user : results) {
            String mail = user.getEmail();
            String account = mail.substring(0, mail.indexOf("@"));
            System.out.println("Email : " + mail + ",Account : " + account);
        }
    }

    //문제 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.
    @Test
    @DisplayName("문제 6")
    void errorDataList() {
        List<Users> users = repository.findAll();
        for (Users user : users) {
            if (user.getCreatedAt().isAfter(user.getUpdatedAt())) {
                System.out.println(user);
            }
        }
    }

    @Test
    @DisplayName("문제 7")
    void findByGenderAndEmailContainsOrderByCreatedAtDesc() {
        repository
                .findByGenderAndEmailContainsOrderByCreatedAtDesc(
                        Gender.Female, "edu"
                )
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 8")
    void likeColorSort() {
        List<Users> result = repository.findAll(
                Sort.by(Sort.Order.asc("likeColor"),
                        Sort.Order.desc("name"))
        );
        for (int i = 0; i <= 20; i++) {
            System.out.println(result.get(i));
        }
    }

    @Test
    @DisplayName("문제 9")
    void sortAndPaging() {
        Sort sort = Sort.by(Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest
                .of(0, 10, sort);
        repository.findAll(pageable).getContent()
                .forEach(x -> System.out.println(x));
    }

    // 문제10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되
    // 그 중 2번째 페이지 자료를  출력하시오.
    @Test
    @DisplayName("문제 10")
    void manDataPaging() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(1, 3, sort);
        repository.findByGender(Gender.Male, pageable)
                .forEach(x-> System.out.println(x));
    }
}
