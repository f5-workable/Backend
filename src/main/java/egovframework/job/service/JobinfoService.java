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
   
   public List<JobinfoDTO> getJobinfoList() {
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
}