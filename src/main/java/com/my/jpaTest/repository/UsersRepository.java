package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // 쿼리메서드 생성
    // 이름으로 검색하기
    // Select * from users where name = '장원영';
    // findByName(String searchName);
    List<Users> findByName(String searchName);

    // 2. 상위 3개 같은 색상 정보 찾기
    // select * from users where color='pink' limit 3;
    List<Users> findTop3ByLikeColor(String color);

    // 3. 셩별이 여자이고 좋아하는 색상이 Red인 자료
    // select * from users where gender='Female' and like_color='Red';
    List<Users> findByGenderAndLikeColor(Gender gender, String color);

    // 4. 범위 검색(날짜, 시간)
    // 어제 이후 생성된 모든 자료 검색하기(어제, 오늘...)
    // select * from users where created_at >= '어제';
    List<Users> findByCreatedAtAfter(LocalDateTime searchDate);
}
