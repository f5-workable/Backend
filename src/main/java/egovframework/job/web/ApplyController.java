package egovframework.job.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egovframework.job.dto.ApplyDTO;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.service.ApplyService;
import egovframework.job.service.CompanyResumeService;
import egovframework.job.vo.ApplyVO;
import egovframework.job.vo.ResumeVO;

@RestController
public class ApplyController {

	@Autowired
	private ApplyService applyService;
	@Autowired
	private CompanyResumeService CompanyResumeService;
	
	//  id별(기본키) 조회 
	@GetMapping("/apply/{id}")
	public ResponseEntity selectOne(@PathVariable Long id) {
		ApplyVO res =  applyService.getApplyById(id);
		return ResponseEntity.ok(res);
	}
	
	// 지원서 등록 (지원 이력서 선택 -> 기업이력서 등록 -> 어플라이 등록(기업이력서+채용공고+상태) )
	@PostMapping("/apply/{j_num}")
	public ResponseEntity addApply(@PathVariable(name="j_num") long j_num, @RequestBody ResumeDTO dto) {
		// 이력서 id로 이력서 조회
		
		// 기업 이력서 등록
		long cr_num = CompanyResumeService.addCompanyResume(dto.toEntity());
		// 등록된 기업 이력서의 cr_num 과 j_num으로 apply 등록
		int res = applyService.addApply(j_num, cr_num);
		return ResponseEntity.ok(res);
	}
	
	// 지원내역 상태별 조회
	@GetMapping("/apply/list/{m_num}")
	public ResponseEntity<List> selectApplyList(@PathVariable(name="m_num") long m_num,
												@RequestParam(name="state", required = false, defaultValue = "all") String state) {
		List<ApplyVO> res = applyService.getApplyListByMemberAndState(m_num, state);	
		return ResponseEntity.ok(res);
	} 
	
	// 지원내역 상태별 갯수의 조회
	@GetMapping("/apply/list/count/{m_num}")
	public ResponseEntity countApplyState(@PathVariable(name="m_num") long m_num){
		List<HashMap> res = applyService.countApplyState(m_num);
		return ResponseEntity.ok(res);
	}
	
	// 지원 취소
	@DeleteMapping("/apply/{a_id}")
	public ResponseEntity deleteApply(@PathVariable long a_id) {
		int res =  applyService.deleteApply(a_id);
		return ResponseEntity.ok(res);
	}
	
	// 지원 상태 변경
	@PutMapping("/company/apply/update")
	public  ResponseEntity updateApplyState(@RequestBody ApplyDTO dto) {
		int res = applyService.updateApplyState(dto); // 1이라면 성공
		return ResponseEntity.ok(res);
	}
	
}
