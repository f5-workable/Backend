package egovframework.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.job.domain.Jobinfo;

public interface JobinfoRepository extends JpaRepository<Jobinfo, Long>{

}
