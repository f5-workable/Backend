package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.JobinfoSearchResponse;
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
	public List<JobinfoSearchResponse> selectWishList(Long memberId) {
		List<JobinfoSearchResponse> res = sqlSession.selectList("egovframework.mapper.job.WishMapper.selectWishList", memberId);
		return res;
	}
//	찜 등록
	public void createWish(WishDTO dto) {
		sqlSession.insert("egovframework.mapper.job.WishMapper.createWish", dto);
	}
//	찜 해제
	public void deleteWish(WishDTO dto) {
		sqlSession.delete("egovframework.mapper.job.WishMapper.deleteWish", dto);
	}
}
