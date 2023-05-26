package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.ResumeDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.ResumeResultVO;
import egovframework.job.vo.ResumeSearchVO;
import egovframework.job.vo.ResumeVO;

@Repository
public class ResumeDAO {

	@Autowired
	private SqlSession sqlSession;
	
//  이력서 전체 조회
	public List<ResumeVO> selectResumeList() {
        List<ResumeVO> resumeDto = sqlSession.selectList("egovframework.mapper.job.ResumeMapper.selectResumeList");
        return resumeDto;
    }
//  한 개의 이력서 조회
    public ResumeDTO selectResumeById(Long id) {
    	ResumeDTO dto = sqlSession.selectOne("egovframework.mapper.job.ResumeMapper.selectResumeById", id);
        return dto;
    }
//  이력서 생성
    public Long addResume(ResumeDTO dto) {
    	ResumeVO vo = dto.toEntity();
       	sqlSession.insert("egovframework.mapper.job.ResumeMapper.createResume", vo);
//      vo.getR_id()도 가능한지 확인
       	return vo.getR_id();
    }
//  이력서 수정
    public void updateResume(ResumeDTO dto) {
       ResumeVO vo = dto.toEntity();
       sqlSession.update("egovframework.mapper.job.ResumeMapper.updateResume", vo);
    }
//  이력서 삭제
    public void deleteResume(Long id) {
       sqlSession.delete("egovframework.mapper.job.ResumeMapper.deleteResume", id);
    }
//  이력서 조건검색
    public List<ResumeResultVO> searchResume(ResumeSearchVO vo) {
//      controller에서 받아온 vo를 selectList에 매개변수로 넣어준다.
       List<ResumeResultVO> res = sqlSession.selectList("egovframework.mapper.job.ResumeMapper.searchResume", vo);
       return res;
    }
//  회원별 이력서조회
    public List<ResumeResultVO> memberResume(Long memberId) {
    	List<ResumeResultVO> res = sqlSession.selectList("egovframework.mapper.job.ResumeMapper.memberResume", memberId);
    	return res;
    }
}
