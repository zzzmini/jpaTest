package com.my.jpaTest.repository;

import com.my.jpaTest.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<UserTest, Long> {
}
