package egovframework.job.dao;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.MemberDTO;

@Repository("memberDAO")
public class MemberDAO extends EgovAbstractMapper {
	
	// 회원가입 처리
	public void registerMember(MemberDTO memberDTO) throws Exception {
        insert("egovframework.mapper.job.MemberMapper.registerMember", memberDTO);
    }

	// 아이디 검색
	public MemberDTO findById(String id) throws Exception {
		return (MemberDTO) selectOne("egovframework.mapper.job.MemberMapper.findById", id);
	}
	
	// 아이디 상세정보
	public MemberDTO getMemberDetail(String id) throws Exception {
		return (MemberDTO) selectOne("egovframework.mapper.job.MemberMapper.findById", id);
	}
	
	// 아이디 상세정보 수정
	public void updateMemberDetail(MemberDTO memberDTO) throws Exception {
		insert("egovframework.mapper.job.MemberMapper.updateMember", memberDTO);
	}
	
	// 아이디 탈퇴
	public void deleteMember(String id) throws Exception {
        delete("egovframework.mapper.job.MemberMapper.deleteMember", id);
    }
	
	// 대표이력서 등록
	public void rdefaultMember(MemberDTO dto) {
		update("egovframework.mapper.job.MemberMapper.rdefaultMember", dto);
	}
}