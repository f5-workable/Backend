package egovframework.job.dao;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.CompanyDTO;

@Repository("companyDAO")
public class CompanyDAO extends EgovAbstractMapper {

	// 회원가입 처리
	public void registerCompany(CompanyDTO companyDTO) throws Exception {
		insert("egovframework.mapper.job.CompanyMapper.registerCompany", companyDTO);
	}

	// 로그인 처리
	public CompanyDTO actionLogin(CompanyDTO companyDTO) throws Exception {
		return (CompanyDTO) selectOne("egovframework.mapper.job.CompanyMapper.actionLogin", companyDTO);
	}

	// 아이디 검색
	public CompanyDTO findById(String id) throws Exception {
		return (CompanyDTO) selectOne("egovframework.mapper.job.CompanyMapper.findById", id);
	}

	// 아이디 상세정보
	public CompanyDTO getCompanyDetail(String id) throws Exception {
		return (CompanyDTO) selectOne("egovframework.mapper.job.CompanyMapper.findById", id);
	}

	// 비밀번호 찾기
	public String findPassword(String c_id, String c_name, String phone) throws Exception {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setC_id(c_id);
		companyDTO.setC_name(c_name);
		companyDTO.setPhone(phone);
		return (String) selectOne("egovframework.mapper.job.CompanyMapper.findPassword", companyDTO);
	}

	// 비밀번호 변경 처리
	public void updatePassword(CompanyDTO companyDTO) throws Exception {
		update("egovframework.mapper.job.CompanyMapper.updatePassword", companyDTO);
	}

	// 아이디 상세정보 수정
	public void updateCompanyDetail(CompanyDTO companyDTO) throws Exception {
		insert("egovframework.mapper.job.CompanyMapper.updateCompany", companyDTO);
	}

	// 아이디 탈퇴
	public void deleteCompany(String id) throws Exception {
		delete("egovframework.mapper.job.CompanyMapper.deleteCompany", id);
	}
}
