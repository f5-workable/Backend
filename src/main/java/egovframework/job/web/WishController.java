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

import egovframework.job.service.WishService;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishCreateVO;

@RestController
public class WishController {

	@Autowired
	private WishService service;
	
//	memberId : 로그인한 회원시퀀스아이디
	@GetMapping("/wish")
	public ResponseEntity selectWishList(@RequestParam Long memberId) {
		List<JobinfoVO> res = service.getWishList(memberId);
		return ResponseEntity.ok(res);
	}
//	찜누를시 create(멤버아이디, 구직정보 시퀀스아이디)
	@PostMapping("/wish")
	public ResponseEntity createWish(@RequestParam Long memberId, @RequestParam Long g_id) {
		WishCreateVO vo = WishCreateVO.builder()
			.memberId(memberId)
			.g_id(g_id)
			.state(true)
			.build();
		service.createWish(vo);
		return ResponseEntity.ok(g_id);
	}
//	찜해제 delete(구직 id)
	@DeleteMapping("/wish")
	public ResponseEntity deleteWish(@RequestParam Long memberId, @RequestParam Long g_id) {
		WishCreateVO vo = WishCreateVO.builder()
			.memberId(memberId)
			.g_id(g_id)
			.build();
       service.deleteWish(vo);
       return ResponseEntity.ok(memberId);
    }
}
