package egovframework.job.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import egovframework.job.dto.WishDTO;
import egovframework.job.service.WishService;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishCreateVO;
import egovframework.job.vo.WishVO;

@RestController
public class WishController {

	@Autowired
	private WishService service;
	
//	memberId : 로그인한 회원시퀀스아이디
	@GetMapping("/wish")
	public ResponseEntity selectWishList(@RequestParam Long memberId
			, @RequestParam(name="pageNum", required = false,  defaultValue = "1")int pageNum
    		, @RequestParam(name="pageSize", required = false,  defaultValue = "12")int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobinfoResultVO> res = service.getWishList(memberId);
		return ResponseEntity.ok(PageInfo.of(res));
	}
//	찜누를시 create(멤버아이디, 구직정보 시퀀스아이디)
	@PostMapping("/wish")
	public ResponseEntity createWish(@RequestParam Long memberId, @RequestParam Long g_id) {
		WishCreateVO vo = WishCreateVO.builder()
			.m_num(memberId)
			.j_num(g_id)
			.build();
		service.createWish(vo);
		return ResponseEntity.ok(g_id);
	}
//	찜해제 delete(구직 id)
	@DeleteMapping("/wish")
	public ResponseEntity deleteWish(@RequestParam Long memberId, @RequestParam Long g_id) {
		WishCreateVO vo = WishCreateVO.builder()
			.m_num(memberId)
			.j_num(g_id)
			.build();
       service.deleteWish(vo);
       return ResponseEntity.ok(memberId);
    }
}
