package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.WishDAO;
import egovframework.job.dto.JobinfoSearchResponse;
import egovframework.job.dto.WishDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishCreateVO;
import egovframework.job.vo.WishVO;

@Service
public class WishService {

	@Autowired
	private WishDAO dao;
	
//	회원별 찜목록 조회
	public List<JobinfoSearchResponse> getWishList(Long memberId) {
		return dao.selectWishList(memberId);
	}
//	찜 등록
	@Transactional
	public void createWish(WishDTO dto) {
		dao.createWish(dto);
	}
//	찜 삭제
	@Transactional
	public void deleteWish(WishDTO dto) {
		dao.deleteWish(dto);
	}
}
