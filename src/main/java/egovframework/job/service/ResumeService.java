package egovframework.job.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import egovframework.job.dao.MemberDAO;
import egovframework.job.dao.ResumeDAO;
import egovframework.job.dao.ResumeRegionDAO;
import egovframework.job.dto.MemberDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.dto.ResumeRegionDTO;
import egovframework.job.dto.ResumeSearchRequest;
import egovframework.job.dto.ResumeSearchResponse;
import egovframework.job.vo.ResumeRegionVO;
import egovframework.job.vo.ResumeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {
	
	private final ResumeDAO dao;
	private final ResumeRegionDAO resumeRegionDAO;
	private final MemberDAO memberDAO;
//	조회
	public List<ResumeDTO> getResumeList() {
        return dao.selectResumeList();
    }
//	id별 조회(이력서지역포함)
    public ResumeDTO getResumeById(Long id) {
//    	이력서 지역 조회
    	List<ResumeRegionVO> list = resumeRegionDAO.selectResumeRegionList(id);
    	ResumeDTO dto = dao.selectResumeById(id);
    	dto.setRegion(list);
//    	해당 이력서를 만든 회원찾기
    	Long mId = dto.getM_num();
    	if (mId != null) {
        	MemberDTO member = memberDAO.findByLongId(mId);
        	dto.setMemberDTO(member);
    	}
        return dto;
    }
//  등록(이력서, 이력서지역)
    @Transactional
    public Long addResume(ResumeDTO dto) {
//     등록한 이력서 id반환
       Long insertId = dao.addResume(dto);
       String[] places = dto.getPlace();
//	   희망지역을 입력했다면
       if (places.length != 0) {
    	   for (String place : places) {
    		   ResumeRegionDTO resumeRegionDTO = ResumeRegionDTO.builder()
    		   		.r_id(insertId)
    		   		.region(place)
    		   		.build();
//        	   이력서 지역등록
        	   resumeRegionDAO.insertResumeRegion(resumeRegionDTO);
           }
       }
       
//     희망지역을 입력하지 않았다면 이력서만 저장
       return insertId;
    }
//  수정
    @Transactional
    public void updateResume(ResumeDTO dto) {
       Long rId = dto.getR_id();
       dao.updateResume(dto);
//     기존에 지역을 모두 삭제후
   	   resumeRegionDAO.deleteResumeRegion(rId);
//     다시 등록(새로운 resume_region의 id로 생성)
	   String[] places = dto.getPlace();
//	   희망지역을 입력했다면
	   if (places.length != 0) {
		   for (String place : places) {
			   ResumeRegionDTO resumeRegionDTO = ResumeRegionDTO.builder()
	    		   		.r_id(rId)
	    		   		.region(place)
	    		   		.build();
		// 	  이력서 지역등록
		 	  resumeRegionDAO.insertResumeRegion(resumeRegionDTO);
		   }
	   }
//	   희망지역을 입력하지 않았다면 그대로 보존
    }
//  삭제
    @Transactional
    public void deleteResume(Long id){
//      이력서 삭제
    	dao.deleteResume(id);
    }
    
    
//  조건검색
    public List<ResumeSearchResponse> searchResume(ResumeSearchRequest req) {
       List<ResumeSearchResponse> res = dao.searchResume(req);
       return res;
    }
//  멤버별 이력서조회
    public List<ResumeSearchResponse> memberResume(Long memberId) {
    	return dao.memberResume(memberId);
    }
 // 이력서 id로 지역 목록 조회
    public List<ResumeRegionVO> selectResumeRegionList(Long resumeId){
       return resumeRegionDAO.selectResumeRegionList(resumeId);
    }
}
