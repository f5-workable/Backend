package egovframework.job.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import egovframework.job.dto.CompanyResumeDTO;
import egovframework.job.service.CompanyResumeService;
import egovframework.job.vo.CompanyResumeVO;

@RestController
public class CompanyResumeController {
	@Autowired
	private CompanyResumeService companyResumeService;
	
	// id에 따른 companyResume 상세 조회
	@GetMapping("/company/apply/detail/{cr_num}")
	public ResponseEntity selectOneCompanyResume(@PathVariable long cr_num) {
		CompanyResumeDTO res =  companyResumeService.seleteById(cr_num);
		return ResponseEntity.ok(res);
	}
}
