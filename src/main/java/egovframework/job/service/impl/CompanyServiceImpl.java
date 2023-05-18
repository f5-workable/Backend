package egovframework.job.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.CompanyDAO;
import egovframework.job.dto.CompanyDTO;
import egovframework.job.service.CompanyService;
import egovframework.let.utl.sim.service.EgovFileScrty;

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
	public void registerCompany(CompanyDTO companyDTO) throws Exception {
		companyDAO.registerCompany(companyDTO);
	}

	// 로그인 처리
	@Override
	public CompanyDTO actionLogin(CompanyDTO companyDTO) throws Exception {
		
		// 1. 입력한 비밀번호를 암호화
		String enpassword = EgovFileScrty.encryptPassword(companyDTO.getC_password(), companyDTO.getC_id());
		companyDTO.setC_password(enpassword);
		
		// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인
		CompanyDTO companyDTO2 = companyDAO.actionLogin(companyDTO);
		
		// 3. 결과를 리턴
		if(companyDTO2 != null && !companyDTO2.getC_id().equals("") && !companyDTO2.getC_password().equals("")) {
			return companyDTO2;
		} else {
			companyDTO2 = new CompanyDTO();
		}
		
		return companyDTO2;
	}

	// 아이디 검색
	@Override
	public CompanyDTO findById(String id) throws Exception {
		return companyDAO.findById(id);
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

	// 아이디 탈퇴
	@Override
	public void deleteCompany(String id) throws Exception {
		companyDAO.deleteCompany(id);
	}
}
