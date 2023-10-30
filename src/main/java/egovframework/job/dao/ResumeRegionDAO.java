package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.ResumeRegionDTO;
import egovframework.job.vo.ResumeRegionVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ResumeRegionDAO {
	
	private final SqlSession sqlSession;
	
	// 지역 등록
	public int insertResumeRegion(ResumeRegionDTO resumeRegionDTO) {
		return sqlSession.insert("egovframework.mapper.job.ResumeRegionMapper.insertResumeRegion", resumeRegionDTO);
	}
	
	// 지역 select
	public List<ResumeRegionVO> selectResumeRegionList (Long r_id) {
		return sqlSession.selectList("egovframework.mapper.job.ResumeRegionMapper.selectResumeRegionByrId",r_id);
	}
	
	// 지역 삭제
	public int deleteResumeRegion(Long region_id) {
		return sqlSession.delete("egovframework.mapper.job.ResumeRegionMapper.deleteResumeRegionByrId",region_id);
		
	}
}
