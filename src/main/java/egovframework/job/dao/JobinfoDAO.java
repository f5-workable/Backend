package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.JobinfoDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoSelectVO;
import egovframework.job.vo.JobinfoVO;

@Repository
public class JobinfoDAO {
   
   @Autowired
   private SqlSession sqlSession;
   
// 구직정보 전체 리스트
   public List<JobinfoVO> selectJobinfoList() {
        List<JobinfoVO> vo = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.selectJobinfoList");
        return vo;
    }
//  구직정보 JobinfoResultVO로 단일 조회
    public JobinfoResultVO selectJobinfoById(Long id) {
    	JobinfoResultVO vo = sqlSession.selectOne("egovframework.mapper.job.JobinfoMapper.selectJobinfoById", id);
        return vo;
    }
//  구직정보 JobinfoVO로 단일 조회
    public JobinfoVO selectJById(Long id) {
    	JobinfoVO vo = sqlSession.selectOne("egovframework.mapper.job.JobinfoMapper.selectJById", id);
    	return vo;
    }
//  구직정보 생성
    public JobinfoVO addJobinfo(JobinfoDTO dto) {
       JobinfoVO vo = dto.toEntity();
       sqlSession.insert("egovframework.mapper.job.JobinfoMapper.createJobinfo", vo);
       return vo;
    }
//  구직정보 수정
    public void updateJobinfo(JobinfoDTO dto) {
       JobinfoVO vo = dto.toEntity();
       sqlSession.update("egovframework.mapper.job.JobinfoMapper.updateJobinfo", vo);
    }
//  구직정보 삭제
    public void deleteJobinfo(Long id) {
       sqlSession.delete("egovframework.mapper.job.JobinfoMapper.deleteJobinfo", id);
    }
//  구직정보 조건검색
    public List<JobinfoResultVO> searchJobinfo(JobinfoSearchVO vo) {
    	List<JobinfoResultVO> res = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.searchJobinfo", vo);
        return res;
    }
    
    public JobinfoResultVO getJobinfoBySelect(JobinfoSelectVO vo) {
    	JobinfoResultVO res = sqlSession.selectOne("egovframework.mapper.job.JobinfoMapper.getJobinfoBySelect", vo);
		return res;
    }
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(long c_num){
    	 List<String> res = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.selectJobTypeByCNum",c_num);
		return res;
    }
}