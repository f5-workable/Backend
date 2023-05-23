package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.ResumeDAO;
import egovframework.job.dao.ResumeRegionDAO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.dto.ResumeRegionDTO;
import egovframework.job.vo.ResumeRegionVO;
import egovframework.job.vo.ResumeResultVO;
import egovframework.job.vo.ResumeSearchVO;
import egovframework.job.vo.ResumeVO;

@Service
public class ResumeService {
	
	@Autowired
	private ResumeDAO dao;
	
	@Autowired
	private ResumeRegionDAO resumeRegionDAO;
//	조회
	public List<ResumeVO> getResumeList() {
        return dao.selectResumeList();
    }
//	id별 조회(이력서지역포함)
    public ResumeDTO getResumeById(Long id) {
//    	이력서 지역 조회
    	List<ResumeRegionVO> list = resumeRegionDAO.selectResumeRegionList(id);
    	ResumeDTO dto = dao.selectResumeById(id);
    	dto.setRegion(list);
    	
        return dto;
    }
//  등록(이력서, 이력서지역)
    @Transactional
    public Long addResume(ResumeDTO dto) {
//     등록한 이력서 id반환
       Long insertId = dao.addResume(dto);
       String[] places = dto.getPlace();
       for (String place : places) {
    	   ResumeRegionDTO resumeRegionDTO = new ResumeRegionDTO();
    	   resumeRegionDTO.setR_id(insertId);
    	   resumeRegionDTO.setRegion(place);
//    	   이력서 지역등록
    	   resumeRegionDAO.insertResumeRegion(resumeRegionDTO);
       }
       return insertId;
    }
//  수정
    @Transactional
    public void updateResume(ResumeDTO dto) {
       Long rId = dto.getR_id();
//     이력서 update
       dao.updateResume(dto);
//     이력서지역 update
//     기존에 있던걸 모두 삭제후
       List<ResumeRegionVO> res = resumeRegionDAO.selectResumeRegionList(rId);
   	   for (ResumeRegionVO resumeRegionVO : res) {
   		   Long id = resumeRegionVO.getRegion_id();
   		   resumeRegionDAO.deleteResumeRegion(id);
	   }
//     다시 등록
	   String[] places = dto.getPlace();
	   for (String place : places) {
	 	  ResumeRegionDTO resumeRegionDTO = new ResumeRegionDTO();
	 	  resumeRegionDTO.setR_id(rId);
	 	  resumeRegionDTO.setRegion(place);
	// 	  이력서 지역등록
	 	  resumeRegionDAO.insertResumeRegion(resumeRegionDTO);
	   }
    }
//  삭제
    @Transactional
    public void deleteResume(Long id) {
//      이력서 삭제
    	dao.deleteResume(id);
//      이력서지역 삭제
    	List<ResumeRegionVO> res = resumeRegionDAO.selectResumeRegionList(id);
    	for (ResumeRegionVO resumeRegionVO : res) {
			id = resumeRegionVO.getRegion_id();
			resumeRegionDAO.deleteResumeRegion(id);
		}
    }
//  조건검색
    public List<ResumeResultVO> searchResume(ResumeSearchVO vo) {
       return dao.searchResume(vo);
    }
    
    // 이력서 id로 지역 목록 조회
    public List<ResumeRegionVO> selectResumeRegionList(long resumeId){
    	return resumeRegionDAO.selectResumeRegionList(resumeId);
    }
}
