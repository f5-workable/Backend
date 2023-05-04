package egovframework.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.job.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
