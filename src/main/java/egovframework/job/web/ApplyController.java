package egovframework.job.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egovframework.job.dto.ApplyDTO;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.service.ApplyService;
import egovframework.job.vo.ApplyVO;

@RestController
public class ApplyController {

   @Autowired
   private ApplyService applyService;
   
   // 지원 목록 리스트 조회
   @GetMapping("/apply")
   public ResponseEntity<List> selectCompany() {
      List<ApplyVO> res = applyService.getApplyList();
      
      return ResponseEntity.ok(res);
   } 
   //  id별(기본키) 조회 
   @GetMapping("/apply/{id}")
   public ResponseEntity selectOne(@PathVariable Long id) {
      ApplyVO res =  applyService.getApplyById(id);
      return ResponseEntity.ok(res);
   }
   
   // 지원서 등록 (지원 이력서 선택 -> 기업이력서 등록 -> 어플라이 등록(기업이력서+채용공고+상태) )
//   @PostMapping("/apply/{j_num}")
//   public ResponseEntity addApply(@PathVariable(name="j_num") long j_num, @RequestBody ResumeDTO dto) {
//      // 기업 이력서 등록
//      long cr_num;
//      // 등록된 기업 이력서의 cr_num 과 j_num으로 apply 등록
//      int res = applyService.addApply(j_num, cr_num);
//      return ResponseEntity.ok(res);
//   }
}