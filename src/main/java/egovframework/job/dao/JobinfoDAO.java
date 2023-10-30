package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.JobinfoDTO;
import egovframework.job.dto.JobinfoSearchRequest;
import egovframework.job.dto.JobinfoSearchResponse;
import egovframework.job.vo.JobinfoVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JobinfoDAO {
   
   private final SqlSession sqlSession;
   
// 구직정보 전체 리스트
   public List<JobinfoDTO> selectJobinfoList() {
        List<JobinfoDTO> dto = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.selectJobinfoList");
        return dto;
    }
    public JobinfoSearchResponse selectJobinfoById(Long id) {
    	JobinfoSearchResponse res = sqlSession.selectOne("egovframework.mapper.job.JobinfoMapper.selectJobinfoById", id);
        return res;
    }
//  구직정보 단일 조회
    public JobinfoDTO selectJById(Long id) {
    	JobinfoDTO dto = sqlSession.selectOne("egovframework.mapper.job.JobinfoMapper.selectJById", id);
    	return dto;
    }
//  구직정보 생성
    public JobinfoDTO addJobinfo(JobinfoDTO dto) {
       sqlSession.insert("egovframework.mapper.job.JobinfoMapper.createJobinfo", dto);
       return dto;
    }
//  구직정보 수정
    public void updateJobinfo(JobinfoDTO dto) {
       sqlSession.update("egovframework.mapper.job.JobinfoMapper.updateJobinfo", dto);
    }
//  구직정보 삭제
    public void deleteJobinfo(Long id) {
       sqlSession.delete("egovframework.mapper.job.JobinfoMapper.deleteJobinfo", id);
    }
//  구직정보 조건검색
    public List<JobinfoSearchResponse> searchJobinfo(JobinfoSearchRequest req) {
    	List<JobinfoSearchResponse> res = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.searchJobinfo", req);
        return res;
    }
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(long c_num){
    	 List<String> res = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.selectJobTypeByCNum",c_num);
		return res;
    }
}