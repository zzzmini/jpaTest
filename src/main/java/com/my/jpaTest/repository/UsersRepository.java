package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // 5. 최근 1개월 자료 검색하기
    List<Users> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // 6. 좋아하는 색상이 Pink, Red 인 모든 자료 출력
    // select * from users where like_color in ('Red', 'Pink');
    // In 구문에는 리스트를 인자로 준다.
    List<Users> findByLikeColorIn(List<String> colors);

    // 7. id가 91번 이상인 자료를 찾아봅니다.
    // >= : GreaterThanEqual,  <= : LessThanEqual
    // > : After, < : Before
    // null 값 비교 : Null or IsNotNull
    List<Users> findByIdGreaterThanEqual(Long id);

    // 8. 문자열 관련 메서드 함수
    // StartingWith : 주어진 문자열로 시작하는 데이터
    // EndingWith : 주어진 문자열로 끝나는 데이터
    // Contains : 포함된 자료
    // Like : 사용 시 넘겨주는 인자 값 양쪽에 %를 붙여주어야 한다.
    // 8.1. 이름이 D로 시작하는 데이터 전체 출력
    // select * from users where name like 'D%';
    List<Users> findByNameStartingWith(String x);

    // 8.2. 이름이 s로 끝나는 데이터 전체 출력
    // select * from users where name like '%s';
    List<Users> findByNameEndingWith(String x);

    // 8.2. email에 org 를 포함하는 데이터(Contains / like)
    // select * from users where email like '%org%';
    List<Users> findByEmailContains(String x);

    List<Users> findByEmailLike(String x);

    // 9. 정렬
    // id : 1 ~ 10까지 이름의 내림차순으로 정렬
    // select * from users where id between 1 to 10 order by name desc;
    List<Users> findByIdBetweenOrderByNameDesc(Long start, Long end);

    // 잠깐 퀴즈.
    // Orange 색상 중 Gender에 오름차순, CreatedAt에 내림차순 후 상위 10개 검색
    // select * from users where like_color='Orange' order by gender asc, created_at desc;
    List<Users> findTop10ByLikeColorOrderByGenderAscCreatedAtDesc(String color);

    // 10. Sort 사용하기
    List<Users> findByLikeColor(String color, Sort sort);

    // 문제 1. 여성의 이름 중 "w"또는 "m"을 포함하는 자료를 검색하시오.
    List<Users> findByGenderAndNameContainsOrGenderAndNameContains
    (Gender gender1, String name1, Gender gender2, String name2);

    // 문제 2. 이메일에 net을 포함하는 데이터 건수를 출력
    // List<Users> findByEmailContains 활용

    // 문제 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 "J"인 자료를 출력
    List<Users> findByUpdatedAtGreaterThanEqualAndNameLike(
            LocalDateTime start, String data
    );

    // 문제 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력
    List<Users> findTop10ByOrderByCreatedAtDesc();

    //문제 5. "Red"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력
    //List<Users> findByGenderAndLikeColor(Gender gender, String color);

    //문제 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.

    //문제 7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력
    List<Users> findByGenderAndEmailContainsOrderByCreatedAtDesc(
            Gender gender, String email
    );

    //문제 8. 좋아하는 색상 별로 오름차순 정렬하고 같은 색상 데이터는
    // 이름의 내림차순으로 출력하시오.
    // Sort() 이용해서 처리

    // 문제 9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징
    // 처리하고 한 페이지당 10건씩 출력하되,
    // 그 중 1번째 페이지를 출력하시오.

    // 문제 10. 남성자료 만.
    List<Users> findByGender(Gender gender, Pageable pageable);
}
