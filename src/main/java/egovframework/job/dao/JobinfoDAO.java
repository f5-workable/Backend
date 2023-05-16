package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.JobinfoDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoVO;

@Repository
public class JobinfoDAO {
   
   @Autowired
   private SqlSession sqlSession;
   
   public List<JobinfoDTO> selectJobinfoList() {
        List<JobinfoDTO> jobinfoDTO = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.selectJobinfoList");
        return jobinfoDTO;
    }

    public JobinfoVO selectJobinfoById(Long id) {
       JobinfoVO vo = sqlSession.selectOne("egovframework.mapper.job.JobinfoMapper.selectJobinfoById", id);
        return vo;
    }
    
    public JobinfoVO addJobinfo(JobinfoDTO dto) {
       JobinfoVO vo = dto.toEntity();
       sqlSession.insert("egovframework.mapper.job.JobinfoMapper.createJobinfo", vo);
       return vo;
    }
    
    public void updateJobinfo(JobinfoDTO dto) {
       JobinfoVO vo = dto.toEntity();
       sqlSession.update("egovframework.mapper.job.JobinfoMapper.updateJobinfo", vo);
    }

    public void deleteJobinfo(Long id) {
       sqlSession.delete("egovframework.mapper.job.JobinfoMapper.deleteJobinfo", id);
    }
    
    public List<JobinfoResultVO> searchJobinfo(JobinfoSearchVO vo) {
//     controller에서 받아온 vo를 selectList에 매개변수로 넣어준다.
       List<JobinfoResultVO> res = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.searchJobinfo", vo);
       return res;
    }
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(long c_num){
    	 List<String> res = sqlSession.selectList("egovframework.mapper.job.JobinfoMapper.selectJobTypeByCNum",c_num);
		return res;
    	
    }
}