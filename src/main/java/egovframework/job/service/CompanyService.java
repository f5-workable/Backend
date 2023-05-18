package egovframework.job.service;

import egovframework.job.dto.CompanyDTO;

public interface CompanyService {

	// 회원가입 처리
	public void registerCompany(CompanyDTO companyDTO) throws Exception;

	// 로그인 처리
	public CompanyDTO actionLogin(CompanyDTO companyDTO) throws Exception;

	// 아이디 검색
	public CompanyDTO findById(String id) throws Exception;

	// 아이디 상세정보
	public CompanyDTO getCompanyDetail(String id) throws Exception;

	// 아이디 상세정보 수정
	public void updateCompanyDetail(CompanyDTO companyDTO) throws Exception;

	// 아이디 탈퇴
	public void deleteCompany(String id) throws Exception;
}
