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
	
	// 아이디 탈퇴
	@Override
	public void deleteMember(String id) throws Exception {
		memberDAO.deleteMember(id);
	}
}
