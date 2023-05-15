package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.vo.WishVO;

@Repository
public class WishDAO {
	
	@Autowired
	private SqlSession sqlSession;
//	로그인한 회원의 찜목록을 가져올때
//	public List<WishVO> selectWish() {
//		List<WishVO> res = sqlSession.selectList("egovframework.mapper.job.WishMapper.", member)
//		return res;
//	}
	
}
