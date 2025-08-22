package com.my.jpaTest.paging;

import com.my.jpaTest.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PagingTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("Paging Test")
    void PagingTest() {
        System.out.println("페이지 = 0, 페이지 당 리스트 수 : 5");
        usersRepository.findAll(
                PageRequest.of(0, 5, Sort.by(Sort.Order.desc("name")))
        ).getContent()
                .forEach(x-> System.out.println(x));
    }
}
