package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.ResumeDTO;
import egovframework.job.dto.ResumeSearchRequest;
import egovframework.job.dto.ResumeSearchResponse;
import egovframework.job.vo.ResumeVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ResumeDAO {

	private final SqlSession sqlSession;
	
//  이력서 전체 조회
	public List<ResumeDTO> selectResumeList() {
        List<ResumeDTO> resumeDto = sqlSession.selectList("egovframework.mapper.job.ResumeMapper.selectResumeList");
        return resumeDto;
    }
//  한 개의 이력서 조회
    public ResumeDTO selectResumeById(Long id) {
    	ResumeDTO dto = sqlSession.selectOne("egovframework.mapper.job.ResumeMapper.selectResumeById", id);
        return dto;
    }
//  이력서 생성
    public Long addResume(ResumeDTO dto) {
       	sqlSession.insert("egovframework.mapper.job.ResumeMapper.createResume", dto);
       	return dto.getR_id();
    }
//  이력서 수정
    public void updateResume(ResumeDTO dto) {
       sqlSession.update("egovframework.mapper.job.ResumeMapper.updateResume", dto);
    }
//  이력서 삭제
    public void deleteResume(Long id) {
       sqlSession.delete("egovframework.mapper.job.ResumeMapper.deleteResume", id);
    }
//  이력서 조건검색
    public List<ResumeSearchResponse> searchResume(ResumeSearchRequest req) {
       List<ResumeSearchResponse> res = sqlSession.selectList("egovframework.mapper.job.ResumeMapper.searchResume", req);
       return res;
    }
//  회원별 이력서조회
    public List<ResumeSearchResponse> memberResume(Long memberId) {
    	List<ResumeSearchResponse> res = sqlSession.selectList("egovframework.mapper.job.ResumeMapper.memberResume", memberId);
    	return res;
    }
}
