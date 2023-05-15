package egovframework.job.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.CompanyResumeDTO;
import egovframework.job.vo.CompanyResumeVO;


@Repository
public class CompanyResumeDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 등록 ->  기업이력서 dto의 cr_num 반환
	public long addCompanyResume(CompanyResumeDTO companyResumeDTO) {
		sqlSession.insert("egovframework.mapper.job.CompanyResumeMapper.addCompanyResume", companyResumeDTO);
		return companyResumeDTO.getCr_num();
	}
	
	// id에 따른 select
	public CompanyResumeVO selectById(long cr_num) {
		return sqlSession.selectOne("egovframework.mapper.job.CompanyResumeMapper.selectCompanyResumeById",cr_num);
	}
	
}
