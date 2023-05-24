package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.WishDAO;
import egovframework.job.dto.WishDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishCreateVO;
import egovframework.job.vo.WishVO;

@Service
public class WishService {

	@Autowired
	private WishDAO dao;
	
	public List<JobinfoResultVO> getWishList(Long memberId) {
		return dao.selectWishList(memberId);
	}
	@Transactional
	public void createWish(WishCreateVO vo) {
		dao.createWish(vo);
	}
	
	@Transactional
	public void deleteWish(WishCreateVO vo) {
		dao.deleteWish(vo);
	}
}
