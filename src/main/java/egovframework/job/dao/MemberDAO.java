package egovframework.job.dao;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.MemberDTO;

@Repository("memberDAO")
public class MemberDAO extends EgovAbstractMapper {

	// 회원가입 처리
	public void insertMember(MemberDTO memberDTO) throws Exception {
		insert("egovframework.mapper.job.MemberMapper.insertMember", memberDTO);
	}

	// 아이디 중복 검색
	public boolean isIdDuplicate(String id) throws Exception {
		Integer count = selectOne("egovframework.mapper.job.MemberMapper.isIdDuplicate", id);
		return count != null && count > 0;
	}

	// 아이디 검색
	public MemberDTO findById(String id) throws Exception {
		return (MemberDTO) selectOne("egovframework.mapper.job.MemberMapper.findById", id);
	}

	// 시퀀스 번호로 회원 조회
	public MemberDTO findByMNum(Long m_num) {
		return (MemberDTO) selectOne("egovframework.mapper.job.MemberMapper.findByMNum", m_num);
	}

	// 아이디 상세정보
	public MemberDTO getMemberDetail(String id) throws Exception {
		return (MemberDTO) selectOne("egovframework.mapper.job.MemberMapper.findById", id);
	}

	// 비밀번호 찾기
	public String findPassword(String id, String name, String phone) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setName(name);
		memberDTO.setPhone(phone);
		return (String) selectOne("egovframework.mapper.job.MemberMapper.findPassword", memberDTO);
	}

	// 비밀번호 변경 처리
	public void updatePassword(MemberDTO memberDTO) throws Exception {
		update("egovframework.mapper.job.MemberMapper.updatePassword", memberDTO);
	}

	// 아이디 상세정보 수정
	public void updateMemberDetail(MemberDTO memberDTO) throws Exception {
		insert("egovframework.mapper.job.MemberMapper.updateMember", memberDTO);
	}

	// 아이디 상세정보 수정
	public void updateSequenceMemberDetail(MemberDTO memberDTO) throws Exception {
		insert("egovframework.mapper.job.MemberMapper.updateMemberSequence", memberDTO);
	}

	// 아이디 탈퇴
	public void deleteMember(String id) throws Exception {
		delete("egovframework.mapper.job.MemberMapper.deleteMember", id);
	}

	// 대표이력서 등록
	public void rdefaultMember(MemberDTO dto) {
		update("egovframework.mapper.job.MemberMapper.rdefaultMember", dto);
	}

	// 아이디 조회(Long)
	public MemberDTO findByLongId(Long id) {
		MemberDTO res = selectOne("egovframework.mapper.job.MemberMapper.findByLongId", id);
		return res;
	}
}