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

	// 아이디 상세정보 수정
	public void updateCompanyDetail(CompanyDTO companyDTO) throws Exception {
		insert("egovframework.mapper.job.CompanyMapper.updateCompany", companyDTO);
	}

	// 아이디 탈퇴
	public void deleteCompany(String id) throws Exception {
		delete("egovframework.mapper.job.CompanyMapper.deleteCompany", id);
	}
}
