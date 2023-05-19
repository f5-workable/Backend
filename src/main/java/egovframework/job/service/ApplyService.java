package egovframework.job.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int addApply(long j_num, long cr_num) {
    	 ApplyDTO dto = new ApplyDTO();
		 dto.setJ_num(j_num);	
		 // 등록된 기업이력서 set
		 dto.setCr_num(cr_num);
    	return applyDAO.createApply(dto);
    }
    
    // 지원내역 상태별 조회 (사용자별 -> 지원상태별)
	public List<Object> getApplyListByMemberAndState(long m_num, String state) {
		if(!(state.equals("지원완료")||state.equals("최종합격")||state.equals("불합격"))) state = null;
		return applyDAO.selectApplyListByMemberAndState(m_num, state);
	}

	// 사용자의 지원내역별 개수 조회
	 public  List<HashMap> countApplyState(long num, String type) {
		 String[] stateList = {"전체", "지원완료", "최종합격", "불합격"};	// 상태 리스트
		// 지원내역별 개수
		// [{"count": 2, "state": "지원완료"}...] 형태로 map을 담을 리스트 
		List<HashMap> resultList = new ArrayList<HashMap>(); 
		for(String state: stateList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("count", applyDAO.countApplyStateByMember(num, type, state));
			if(type.equals("company") && state == "지원완료") { 
				state = "대기";
			}
			 map.put("state", state); 
			// map {"count": 2, "state": "지원완료"} 을 리스트에 추가
			resultList.add(map);
		}
		return resultList;
	 }
	
	// 지원 취소
	public int deleteApply(long a_id) {
		return applyDAO.deleteApply(a_id);
	}
	
	// 지원 상태 변경
	public int updateApplyState(ApplyDTO dto) {
		return applyDAO.updateApplyState(dto);
	}
	
	// apply id로 해당 cr_num 조회
	public long selecteCrNumById(long a_id) {
		return applyDAO.selecteCrNumById(a_id);
	}
	
	// 기업 - 업종별 지원 내역 목록 조회
	public List<Object> selecteCRAndMemberById(long j_num, String state){
		if(state.equals("대기")) 
			state = "지원완료";
		else if(!(state.equals("최종합격") || state.equals("불합격"))) 
			state = null;

		ApplyDTO dto = new ApplyDTO();
		dto.setJ_num(j_num);		
		dto.setState(state);
		return applyDAO.selecteCRAndMemberById(dto);
	}
	
	// 지원자 통계
	public HashMap<String, Object> statisticsApply(long j_num){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total_cnt",applyDAO.statisticsCount(j_num));
		resultMap.put("ob_type",applyDAO.statisticsObType(j_num));
		resultMap.put("disease",applyDAO.statisticsDisease(j_num));
		resultMap.put("gender",applyDAO.statisticsGender(j_num));
		resultMap.put("education",applyDAO.statisticsEducation(j_num));
		resultMap.put("age",applyDAO.statisticsAge(j_num));
		
		return resultMap;
	}
	
}
