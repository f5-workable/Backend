package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.CompanyResumeRegionDTO;
import egovframework.job.vo.CompanyResumeRegionVO;
@Repository
public class CompanyResumeRegionDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 등록 ->  기업이력서_지역 dto의 cr_region_id 추가
	public int insertCrRegion(CompanyResumeRegionDTO companyResumeRegionDTO) {
		return sqlSession.insert("egovframework.mapper.job.CompanyResumeRegionMapper.insertCrRegion", companyResumeRegionDTO);
	}
	
	// cr_num에 따른 select
	public List<CompanyResumeRegionVO> selectCrResumeList (long cr_num) {
		return sqlSession.selectList("egovframework.mapper.job.CompanyResumeRegionMapper.selectCrRegionByCrNum",cr_num);
	}
	
	// cr_num에 따른 삭제
	public int deleteCrResume(long cr_num) {
		return sqlSession.delete("egovframework.mapper.job.CompanyResumeRegionMapper.deleteCrRegionByCrNum",cr_num);
		
	}
}
