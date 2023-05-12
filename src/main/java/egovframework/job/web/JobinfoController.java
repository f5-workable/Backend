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

import egovframework.job.dto.JobinfoDTO;
import egovframework.job.service.JobinfoService;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoVO;

@RestController
public class JobinfoController {
   @Autowired
   private JobinfoService service;
//   
   @GetMapping("/jobinfo")
   public ResponseEntity<List> selectJobinfo() {
      List<JobinfoDTO> res =  service.getJobinfoList();
//      }
      return ResponseEntity.ok(res);
   }
//   id별(기본키) 조회 
   @GetMapping("/jobinfo/{id}")
   public ResponseEntity selectOne(@PathVariable Long id) {
      JobinfoVO res =  service.getJobinfoById(id);
      return ResponseEntity.ok(res);
   }
//   Create
   @PostMapping("/jobinfo")
   public ResponseEntity createJobinfo(@RequestBody JobinfoDTO dto) {
      JobinfoVO vo = service.addJobinfo(dto);
      return ResponseEntity.ok(vo);
   }
//   Update
    @PutMapping("/jobinfo/{id}")
    public ResponseEntity updateJobinfo(@PathVariable Long id, @RequestBody JobinfoDTO dto) {
//       dto의 j_id : null, 쿼리스트링으로 받아온 id값을 set
       dto.setJ_id(id);
       service.updateJobinfo(dto);       
       JobinfoVO res = service.getJobinfoById(id);
       return ResponseEntity.ok(res);
    }
//  Delete
    @DeleteMapping("/jobinfo/{id}")
    public ResponseEntity deleteJobinfo(@PathVariable Long id) {
       service.deleteJobinfo(id);
       return ResponseEntity.ok(id);
    }
//  조건 검색
    @GetMapping("/jobinfo/search")
    public ResponseEntity searchJobinfo(@RequestBody JobinfoSearchVO vo) {
       List<JobinfoVO> res = service.searchJobinfo(vo);
       return ResponseEntity.ok(res);
    }
}