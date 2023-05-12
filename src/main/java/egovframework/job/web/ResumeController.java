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
import org.springframework.web.bind.annotation.RestController;

import egovframework.job.dto.ResumeDTO;
import egovframework.job.service.ResumeService;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.ResumeSearchVO;
import egovframework.job.vo.ResumeVO;

@RestController
public class ResumeController {
	
	@Autowired
	private ResumeService service;
//	전체 조회
	@GetMapping("/resume")
	public ResponseEntity<List> selectResumeList() {
		List<ResumeDTO> res =  service.getResumeList();
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
		ResumeVO res = service.addResume(resumeDto);
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
//  조건 검색
    @GetMapping("/resume/search")
    public ResponseEntity searchJobinfo(@RequestBody ResumeSearchVO resumeDto) {
    	List<ResumeSearchVO> res = service.searchResume(resumeDto);
        return ResponseEntity.ok(res);
    }
}
