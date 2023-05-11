package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.MemberDTO;
import egovframework.job.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
    private SqlSession sqlSession;

	public List<MemberDTO> selectMemberList() {
        List<MemberVO> memberVOList = sqlSession.selectList("egovframework.mapper.job.MemberMapper.selectMemberList");
        return MemberDTO.of(memberVOList);
    }

    public MemberDTO selectMemberById(String id) {
        MemberVO memberVO = sqlSession.selectOne("egovframework.mapper.job.MemberMapper.selectMemberById", id);
        return MemberDTO.of(memberVO);
    }

    public int insertMember(MemberDTO memberDTO) {
        MemberVO memberVO = memberDTO.toEntity();
        return sqlSession.insert("egovframework.mapper.job.MemberMapper.insertMember", memberVO);
    }

    public int updateMember(MemberDTO memberDTO) {
        MemberVO memberVO = memberDTO.toEntity();
        return sqlSession.update("egovframework.mapper.job.MemberMapper.updateMember", memberVO);
    }

    public int deleteMember(String id) {
        return sqlSession.update("egovframework.mapper.job.MemberMapper.deleteMember", id);
    }
}
