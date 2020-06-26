package com.health_eat.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MembersRepository extends JpaRepository<Members, Long>, JpaSpecificationExecutor<Members> {
    Members findByMemberId(String memberId);
    boolean existsMemberByMemberId(String memberId);
}
