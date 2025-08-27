package com.my.jpaTest.repository;

import com.my.jpaTest.dto.MemberProjection;
import com.my.jpaTest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("SELECT m.name AS memberName, t.teamName AS teamName " +
            "FROM Member m JOIN m.team t")
    List<MemberProjection> findProjection();
}
