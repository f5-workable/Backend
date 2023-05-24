package egovframework.job.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import egovframework.job.dto.ResumeDTO;
import egovframework.job.service.ResumeService;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.ResumeResultVO;
import egovframework.job.vo.ResumeSearchVO;
import egovframework.job.vo.ResumeVO;

@RestController
public class ResumeController {
	
	@Autowired
	private ResumeService service;
//	전체 조회
	@GetMapping("/resume")
	public ResponseEntity selectResumeList() {
		List<ResumeVO> res =  service.getResumeList();
		return ResponseEntity.ok(res);
	}
//  id별(기본키) 조회 
	@GetMapping("/resume/{id}")
	public ResponseEntity selectOne(@PathVariable Long id) {
		ResumeDTO res =  service.getResumeById(id);
		return ResponseEntity.ok(res);
	}
//  Create
	@PostMapping("/resume")
	public ResponseEntity createResume(@RequestBody ResumeDTO resumeDto) {
		Long res = service.addResume(resumeDto);
		return ResponseEntity.ok(res);
	}
//  Update
	@PutMapping("/resume/{id}")
	public ResponseEntity updateResume(@PathVariable Long id, @RequestBody ResumeDTO resumeDto) {
//      dto의 r_id : null, 쿼리스트링으로 받아온 id값을 set
		resumeDto.setR_id(id);
		service.updateResume(resumeDto);
		ResumeDTO res = service.getResumeById(id);
		return ResponseEntity.ok(res);
	}
//  Delete
    @DeleteMapping("/resume/{id}")
    public ResponseEntity deleteResume(@PathVariable Long id) {
       service.deleteResume(id);
       return ResponseEntity.ok(id);
    }
//  조건검색
    @GetMapping("/resume/search")
    public ResponseEntity searchResume(@RequestParam("payment_type") String[] payment_type, @RequestParam("disease") String[] disease, @RequestParam("ob_type") String[] ob_type, @RequestParam("place") String[] place, @RequestParam("education") String[] education, @RequestParam("keyword") String keyword) {
    	PageHelper.startPage(1,3);
    	ResumeSearchVO vo = ResumeSearchVO.builder()
    			.payment_type(payment_type)
    			.disease(disease)
    			.ob_type(ob_type)
    			.place(place)
    			.education(education)
    			.keyword(keyword)
    			.build();
    	List<ResumeResultVO> res = service.searchResume(vo);
    	return ResponseEntity.ok(PageInfo.of(res));
    }
//  이력서 관리(회원별 모든 이력서 조회) -> 이력서 시퀀스아이디값 넘겨주기(모두)
//	memberId : 로그인한 회원시퀀스아이디
	@GetMapping("/resume/member")
	public ResponseEntity selectWishList(@RequestParam Long memberId) {
		PageHelper.startPage(1,3);
		List<ResumeResultVO> res = service.memberResume(memberId);
		return ResponseEntity.ok(PageInfo.of(res));
	}
    
}