package egovframework.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.CompanyResumeDAO;
import egovframework.job.dto.CompanyResumeDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.vo.CompanyResumeVO;
import egovframework.job.vo.ResumeVO;

@Service
public class CompanyResumeService {

	@Autowired
	private CompanyResumeDAO companyResumeDAO;
	// 등록 (이력서 -> 기업이력서)
	public long addCompanyResume(ResumeVO resumeVO) {
		CompanyResumeDTO insertDto = new CompanyResumeDTO();
		insertDto.toCompanyResume(resumeVO);	//이력서 -> 기업이력서
		return companyResumeDAO.addCompanyResume(insertDto);
	}
	
	// id에 따른 기업이력서 조회
	public CompanyResumeVO seleteById(long cr_num) {
		return companyResumeDAO.selectById(cr_num);
	}
	
	// id에 따른 기업이력서 삭제
	public int deleteById(long cr_num) {
		return companyResumeDAO.deleteById(cr_num);
	}

}
