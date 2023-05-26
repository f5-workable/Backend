package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.WishDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishCreateVO;
import egovframework.job.vo.WishVO;

@Repository
public class WishDAO {
	
	@Autowired
	private SqlSession sqlSession;
//	한 회원의 찜목록
	public List<JobinfoResultVO> selectWishList(Long memberId) {
		List<JobinfoResultVO> res = sqlSession.selectList("egovframework.mapper.job.WishMapper.selectWishList", memberId);
		return res;
	}
//	찜 드록
	public void createWish(WishCreateVO vo) {
		sqlSession.insert("egovframework.mapper.job.WishMapper.createWish", vo);
	}
//	찜 해제
	public void deleteWish(WishCreateVO vo) {
		sqlSession.delete("egovframework.mapper.job.WishMapper.deleteWish", vo);
	}
}
