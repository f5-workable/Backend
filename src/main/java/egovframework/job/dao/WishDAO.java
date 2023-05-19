package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishCreateVO;

@Repository
public class WishDAO {
	
	@Autowired
	private SqlSession sqlSession;
//	로그인한 회원의 찜목록을 가져올때
	public List<JobinfoVO> selectWishList(Long memberId) {
		List<JobinfoVO> res = sqlSession.selectList("egovframework.mapper.job.WishMapper.selectWishList", memberId);
		return res;
	}
//	찜클릭시 생성
	public void createWish(WishCreateVO vo) {
		sqlSession.insert("egovframework.mapper.job.WishMapper.createWish", vo);
	}
//	찜 해제
	public void deleteWish(WishCreateVO vo) {
		sqlSession.delete("egovframework.mapper.job.WishMapper.deleteWish", vo);
	}
}
