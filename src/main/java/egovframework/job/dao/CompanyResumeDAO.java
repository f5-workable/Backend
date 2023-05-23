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
	
	// 등록 
	public CompanyResumeDTO addCompanyResume(CompanyResumeDTO companyResumeDTO) {
		sqlSession.insert("egovframework.mapper.job.CompanyResumeMapper.addCompanyResume", companyResumeDTO);
		return companyResumeDTO;
	}
	
	// id에 따른 select
	public CompanyResumeDTO selectById(long cr_num) {
		return sqlSession.selectOne("egovframework.mapper.job.CompanyResumeMapper.selectCompanyResumeById",cr_num);
	}
	
	// id에 따른 삭제
	public int deleteById(long cr_num) {
		return sqlSession.delete("egovframework.mapper.job.CompanyResumeMapper.deleteCompanyResumeById",cr_num);
		
	}

}
