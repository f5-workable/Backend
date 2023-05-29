package egovframework.job.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import egovframework.job.dao.CompanyDAO;
import egovframework.job.dto.CompanyDTO;
import egovframework.job.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl extends EgovAbstractServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	// Constructor injection
	public CompanyServiceImpl(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	// 회원가입 처리
	@Override
	public void insertCompany(CompanyDTO companyDTO) throws Exception {
		companyDAO.insertCompany(companyDTO);
	}

	// 아이디 검색
	@Override
	public CompanyDTO findById(String id) throws Exception {
		return companyDAO.findById(id);
	}

	// 시퀀스 넘버 검색
	@Override
	public CompanyDTO findByCNum(Long c_num) {
		return companyDAO.findByCNum(c_num);
	}

	// 아이디 상세정보
	@Override
	public CompanyDTO getCompanyDetail(String id) throws Exception {
		return companyDAO.getCompanyDetail(id);
	}

	// 아이디 상세정보 수정
	@Override
	public void updateCompanyDetail(CompanyDTO companyDTO) throws Exception {
		companyDAO.updateCompanyDetail(companyDTO);
	}

	// 아이디 상세정보 수정
	@Override
	public void updateSequenceCompanyDetail(CompanyDTO companyDTO) throws Exception {
		companyDAO.updateSequenceCompanyDetail(companyDTO);
	}

	// 비밀번호 찾기
	@Override
	public String findPassword(String c_id, String c_name, String phone) throws Exception {
		return companyDAO.findPassword(c_id, c_name, phone);
	}

	// 비밀번호 변경
	@Override
	public void updatePassword(CompanyDTO companyDTO) throws Exception {
		String encryptedPassword = encryptPassword(companyDTO.getC_password());
		companyDTO.setC_password(encryptedPassword);
		companyDAO.updatePassword(companyDTO);
	}

	// 암호화된 비밀번호 반환
	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	// 아이디 탈퇴
	@Override
	public void deleteCompany(String id) throws Exception {
		companyDAO.deleteCompany(id);
	}
}
