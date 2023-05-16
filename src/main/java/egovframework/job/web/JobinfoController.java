package egovframework.job.web;

import java.util.Iterator;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import egovframework.job.dto.JobinfoDTO;
import egovframework.job.service.JobinfoService;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoVO;

@RestController
public class JobinfoController {
   @Autowired
   private JobinfoService service;
//   
   @GetMapping("/jobinfo")
   public ResponseEntity<List> selectJobinfo() {
      List<JobinfoVO> res =  service.getJobinfoList();
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
   public ResponseEntity createJobinfo(@RequestBody JobinfoDTO jobinfoDto) {
      JobinfoVO res = service.addJobinfo(jobinfoDto);
      return ResponseEntity.ok(res);
   }
//   Update
    @PutMapping("/jobinfo/{id}")
    public ResponseEntity updateJobinfo(@PathVariable Long id, @RequestBody JobinfoDTO jobinfoDto) {
//       dto의 j_id : null, 쿼리스트링으로 받아온 id값을 set
       jobinfoDto.setJ_id(id);
       service.updateJobinfo(jobinfoDto);       
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
    public ResponseEntity searchJobinfo(@RequestBody JobinfoSearchVO jobinfosearchVO) {
    	List<JobinfoResultVO> res = service.searchJobinfo(jobinfosearchVO);
        return ResponseEntity.ok(res);
    }
    
//  페이징 PageHelper  
//    @GetMapping("/jobinfo/search")
//    public ResponseEntity searchJobinfo(@RequestBody JobinfoSearchVO jobinfosearchVO) {
////    	pageNum:현재 페이지, pageSize:페이지당 수량
//    	PageHelper.startPage(0, 3);
//    	List<JobinfoResultVO> res = service.searchJobinfo(jobinfosearchVO);
//        return ResponseEntity.ok(PageInfo.of(res));
//    }
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    @GetMapping("/jobinfo/jobtype/{c_num}")
    public ResponseEntity selectJobTypeByCNum(@PathVariable long c_num) {
    	
     	List<String> res = service.selectJobTypeByCNum(c_num);
        return ResponseEntity.ok(res);
    }
}