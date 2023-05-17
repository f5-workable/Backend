package egovframework.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.CompanyResumeDAO;
import egovframework.job.dto.CompanyResumeDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.vo.ResumeVO;

@Service
public class CompanyResumeService {

   @Autowired
   private CompanyResumeDAO dao;
   // 등록 (이력서 -> 기업이력서)
	/*
	 * public long addCompanyResume(ResumeVO resumeVO) { CompanyResumeDTO insertDto
	 * = new CompanyResumeDTO(); insertDto.toCompanyResume(resumeVO); //이력서 -> 기업이력서
	 * 
	 * dao.addCompanyResume(insertDto); // insert return insertDto.getCr_num();
	 * 
	 * }
	 */
}