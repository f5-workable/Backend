package egovframework.job.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.MemberDAO;
import egovframework.job.dto.MemberDTO;
import egovframework.job.service.MemberService;
import egovframework.let.utl.sim.service.EgovFileScrty;

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

	// 로그인 처리
	@Override
	public MemberDTO actionLogin(MemberDTO memberDTO) throws Exception {

		// 1. 입력한 비밀번호를 암호화
		String enpassword = EgovFileScrty.encryptPassword(memberDTO.getPassword(), memberDTO.getId());
		memberDTO.setPassword(enpassword);

		// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인
		MemberDTO memberDTO2 = memberDAO.actionLogin(memberDTO);

		// 3. 결과를 리턴
		if (memberDTO2 != null && !memberDTO2.getId().equals("") && !memberDTO2.getPassword().equals("")) {
			return memberDTO2;
		} else {
			memberDTO2 = new MemberDTO();
		}

		return memberDTO2;
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
