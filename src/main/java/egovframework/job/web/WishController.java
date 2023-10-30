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

import egovframework.job.dto.JobinfoSearchResponse;
import egovframework.job.dto.WishDTO;
import egovframework.job.service.WishService;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WishController {

	private final WishService service;
	
//	한 회원의 찜목록
	@GetMapping("/wish")
	public ResponseEntity selectWishList(@RequestParam Long memberId
			, @RequestParam(name="pageNum", required = false,  defaultValue = "1")int pageNum
    		, @RequestParam(name="pageSize", required = false,  defaultValue = "12")int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobinfoSearchResponse> res = service.getWishList(memberId);
		return ResponseEntity.ok(PageInfo.of(res));
	}
//	찜등록 create
	@PostMapping("/wish")
	public ResponseEntity createWish(@RequestParam Long memberId, @RequestParam Long g_id) {
		WishDTO dto = WishDTO.builder()
			.m_num(memberId)
			.j_num(g_id)
			.build();
		service.createWish(dto);
		return ResponseEntity.ok(g_id);
	}
//	찜해제 delete
	@DeleteMapping("/wish")
	public ResponseEntity deleteWish(@RequestParam Long memberId, @RequestParam Long g_id) {
		WishDTO dto = WishDTO.builder()
			.m_num(memberId)
			.j_num(g_id)
			.build();
       service.deleteWish(dto);
       return ResponseEntity.ok(memberId);
    }
}
