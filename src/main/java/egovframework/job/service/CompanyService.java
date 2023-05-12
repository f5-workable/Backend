package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.CompanyDAO;
import egovframework.job.dto.CompanyDTO;
import egovframework.job.vo.CompanyVO;

@Service
public class CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	public List<CompanyVO> getCompanyList() {
		return companyDAO.selectCompanyList();
	}

	public CompanyVO getCompanyById(String id) {
		return companyDAO.selectCompanyById(id);
	}
	
	@Transactional
	public void addCompany(CompanyDTO companydto) {
		companyDAO.insertCompany(companydto);
	}
	
	@Transactional
	public void updateCompany(CompanyDTO companydto) {
		companyDAO.updateCompany(companydto);
	}
	
	@Transactional
	public void deleteCompany(String id) {
		companyDAO.deleteCompany(id);
	}
}
