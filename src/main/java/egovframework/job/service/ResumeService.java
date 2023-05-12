package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.ResumeDAO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.ResumeSearchVO;
import egovframework.job.vo.ResumeVO;

@Service
public class ResumeService {
	
	@Autowired
	private ResumeDAO dao;
	
	public List<ResumeDTO> getResumeList() {
        return dao.selectResumeList();
    }
	
    public ResumeDTO getResumeById(Long id) {
        return dao.selectResumeById(id);
    }
    
    @Transactional
    public ResumeVO addResume(ResumeDTO dto) {
       ResumeVO vo = dao.addResume(dto);
       return vo;
    }
    
    @Transactional
    public void updateResume(ResumeDTO dto) {
       dao.updateResume(dto);
    }
    
    @Transactional
    public void deleteResume(Long id) {
       dao.deleteResume(id);
    }
    
    public List<ResumeSearchVO> searchResume(ResumeSearchVO vo) {
       return dao.searchResume(vo);
    }
}
