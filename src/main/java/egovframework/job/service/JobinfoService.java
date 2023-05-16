package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.JobinfoDAO;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoVO;

@Service
public class JobinfoService {

   @Autowired
   private JobinfoDAO dao;
   
<<<<<<< .merge_file_a3w768
   public List<JobinfoDTO> getJobinfoList() {
=======
   public List<JobinfoVO> getJobinfoList() {
>>>>>>> .merge_file_ydSCQt
        return dao.selectJobinfoList();
    }

    public JobinfoVO getJobinfoById(Long id) {
        return dao.selectJobinfoById(id);
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
       return dao.searchJobinfo(vo);
    }
<<<<<<< .merge_file_a3w768
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(long c_num){
    	return dao.selectJobTypeByCNum(c_num);
    }
    
=======
>>>>>>> .merge_file_ydSCQt
}