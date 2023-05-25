package egovframework.job.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.MemberDAO;
import egovframework.job.dto.MemberDTO;
import egovframework.job.service.MemberService;

@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	// Constructor injection
	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	// 회원가입 처리
	@Override
	public void registerMember(MemberDTO memberDTO) throws Exception {
		memberDAO.registerMember(memberDTO);
	}

	// 아이디 중복 검색
	public boolean isIdDuplicate(String id) throws Exception {
		MemberDTO existingMember = memberDAO.findById(id);
		return  existingMember != null;
	}

	// 아이디 검색
	@Override
	public MemberDTO findById(String id) throws Exception {
		return memberDAO.findById(id);
	}

	// 아이디 상세정보
	@Override
	public MemberDTO getMemberDetail(String id) throws Exception {
		return memberDAO.getMemberDetail(id);
	}

	// 아이디 상세정보 수정
	@Override
	public void updateMemberDetail(MemberDTO memberDTO) throws Exception {
		memberDAO.updateMemberDetail(memberDTO);
	}
	
	// 비밀번호 찾기
	@Override
    public String findPassword(String id, String name, String phone) throws Exception {
        return memberDAO.findPassword(id, name, phone);
    }
	
	// 비밀번호 변경
	@Override
    public void changePassword(String id, String name, String phone, String newPassword) throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setName(name);
        memberDTO.setPhone(phone);
        memberDTO.setPassword(newPassword);
        memberDAO.updatePassword(memberDTO);
    }

	// 아이디 탈퇴
	@Override
	public void deleteMember(String id) throws Exception {
		memberDAO.deleteMember(id);
	}

	// 대표이력서 등록
	@Override
	public void rdefaultMember(MemberDTO dto) {
		memberDAO.rdefaultMember(dto);
	}
	
	// 시퀀스아이디로 검색
	@Override
	public MemberDTO findByLongId(Long id) {
		MemberDTO res = memberDAO.findByLongId(id);
		return res;
	}
}
