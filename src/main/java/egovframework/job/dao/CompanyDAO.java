package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.CompanyDTO;
import egovframework.job.vo.CompanyVO;

@Repository
public class CompanyDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<CompanyVO> selectCompanyList() {
		List<CompanyVO> companyVO = sqlSession.selectList("egovframework.mapper.job.CompanyMapper.selectCompanyList");

		return companyVO;
	}

	public CompanyVO selectCompanyById(String id) {
		CompanyVO companyVO = sqlSession.selectOne("egovframework.mapper.job.CompanyMapper.selectCompanyById", id);

		return companyVO;
	}
	
	public int insertCompany(CompanyDTO companyDTO) {
        CompanyVO companyVO = companyDTO.toEntity();
        
        return sqlSession.insert("egovframework.mapper.job.CompanyMapper.insertCompany", companyVO);
    }
	
	public int updateCompany(CompanyDTO companyDTO) {
        CompanyVO companyVO = companyDTO.toEntity();
        
        return sqlSession.update("egovframework.mapper.job.CompanyMapper.updateCompany", companyVO);
    }
	
	public int deleteCompany(String id) {
		return sqlSession.delete("egovframework.mapper.job.CompanyMapper.deleteCompany", id);
	}
	
}
