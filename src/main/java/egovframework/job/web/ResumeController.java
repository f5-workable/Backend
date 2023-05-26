package egovframework.job.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import egovframework.job.dto.MemberDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.service.MemberService;
import egovframework.job.service.ResumeService;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.MemberVO;
import egovframework.job.vo.ResumeResultVO;
import egovframework.job.vo.ResumeSearchVO;
import egovframework.job.vo.ResumeVO;

@RestController
public class ResumeController {
	
	@Autowired
	private ResumeService service;
	
	@Autowired
	private MemberService memberService;
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
	public ResponseEntity createResume(@Valid @RequestBody ResumeDTO resumeDto) {
//		해당 회원아이디의 시퀀스값을 이력서에 넣어준다
		Long res = service.addResume(resumeDto);
		return ResponseEntity.ok(res);
	}
//  Update
	@PutMapping("/resume/{id}")
	public ResponseEntity updateResume(@PathVariable Long id, @RequestBody ResumeDTO resumeDto) {
//	    dto의 r_id : null, 쿼리스트링으로 받아온 id값을 set
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
    public ResponseEntity searchResume(@RequestParam("payment_type") String[] payment_type, @RequestParam("disease") String[] disease, @RequestParam("ob_type") String[] ob_type, @RequestParam("place") String[] place, @RequestParam("education") String[] education, @RequestParam("keyword") String keyword, @RequestParam("sort") String sort
    		, @RequestParam(name="pageNum", required = false,  defaultValue = "1")int pageNum
    		, @RequestParam(name="pageSize", required = false,  defaultValue = "12")int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	ResumeSearchVO vo = ResumeSearchVO.builder()
    			.payment_type(payment_type)
    			.disease(disease)
    			.ob_type(ob_type)
    			.place(place)
    			.education(education)
    			.keyword(keyword)
    			.sort(sort)
    			.build();
    	List<ResumeResultVO> res = service.searchResume(vo);
    	return ResponseEntity.ok(PageInfo.of(res));
    }
    
//  이력서 관리(회원별 모든 이력서 조회) -> 이력서 시퀀스아이디값 넘겨주기(모두)
//	회원별 이력서조회
    @GetMapping("/resume/member")
	public ResponseEntity selectWishList(@RequestParam(required=false) Long memberId) throws Exception {
		PageHelper.startPage(1,3);
		List<ResumeResultVO> res = service.memberResume(memberId);
		// 로그인한 회원만이 해당 API를 실행가능(따로 memberId여부를 따지지 않음)
		// 대표이력서 설정
		return ResponseEntity.ok(PageInfo.of(res));
	}
//  대표이력서 설정
    @GetMapping("/resume/rdefault")
    public ResponseEntity<?> selectRdefault(@RequestParam(required=false) Long memberId , @RequestParam(required=false) Long r_id) throws Exception {
//    	회원정보 조회
    	MemberDTO dto = memberService.findByLongId(memberId);
    	// 대표이력서 설정
		if (r_id != null) {
	    	dto.setR_default(r_id);
	    	memberService.rdefaultMember(dto);
		}
		else {
			dto.setR_default(null);
		}
    	return ResponseEntity.ok(dto);
    }
}