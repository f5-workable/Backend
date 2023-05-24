package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.JobinfoDAO;
import egovframework.job.dao.WishDAO;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishVO;

@Service
public class JobinfoService {

   @Autowired
   private JobinfoDAO dao;
   
   @Autowired
   private WishDAO wishdao;
   

   public List<JobinfoVO> getJobinfoList() {
        return dao.selectJobinfoList();
    }

    public JobinfoResultVO getJobinfoById(Long id) {
        return dao.selectJobinfoById(id);
    }
    
    public JobinfoVO getJById(Long id) {
    	return dao.selectJById(id);
    }
    
    @Transactional
    public JobinfoVO addJobinfo(JobinfoDTO dto) {
       JobinfoVO vo = dao.addJobinfo(dto);
       return vo;
    }
    
    @Transactional
    public void updateJobinfo(JobinfoDTO dto) {
       dao.updateJobinfo(dto);
    }
    
    @Transactional
    public void deleteJobinfo(Long id) {
       dao.deleteJobinfo(id);
    }
    
    public List<JobinfoResultVO> searchJobinfo(JobinfoSearchVO vo) {
    	List<JobinfoResultVO> res = dao.searchJobinfo(vo);
       return res;
    }
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(long c_num){
    	return dao.selectJobTypeByCNum(c_num);
    }
    
}