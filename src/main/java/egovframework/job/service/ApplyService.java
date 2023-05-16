package egovframework.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.ApplyDAO;
import egovframework.job.dto.ApplyDTO;
import egovframework.job.vo.ApplyVO;

@Service
public class ApplyService {
   @Autowired
   private ApplyDAO applyDAO;
   
   
   public List<ApplyVO> getApplyList() {
      return applyDAO.selectApplyList();
   }
   
    public ApplyVO getApplyById(Long id) {
        return applyDAO.selectApplyById(id);
    }
    
    // 지원 등록
	/*
	 * public int addApply(long j_num, long cr_num) { ApplyDTO dto = new ApplyDTO();
	 * dto.setJ_num(j_num); // 등록된 기업이력서 set dto.setCr_num(cr_num);
	 * 
	 * return applyDAO.createApply(dto); }
	 */
}