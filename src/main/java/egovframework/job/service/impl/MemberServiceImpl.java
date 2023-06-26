package egovframework.job.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public void insertMember(MemberDTO memberDTO) throws Exception {
		memberDAO.insertMember(memberDTO);
	}

	// 아이디 중복 검색
	@Override
	public boolean isIdDuplicate(String id) throws Exception {
		return memberDAO.isIdDuplicate(id);
	}

	// 아이디 검색
	@Override
	public MemberDTO findById(String id) throws Exception {
		return memberDAO.findById(id);
	}

	// 시퀀스 넘버 검색
	@Override
	public MemberDTO findByMNum(Long m_num) {
		return memberDAO.findByMNum(m_num);
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

	// 아이디 상세정보 수정
	@Override
	public void updateSequenceMemberDetail(MemberDTO memberDTO) throws Exception {
		memberDAO.updateSequenceMemberDetail(memberDTO);
	}

	// 이전 비밀번호 찾기
	@Override
	public String getPasswordByMNum(Long m_num) throws Exception {
		return memberDAO.getPasswordByMNum(m_num);
	}

	// 비밀번호 찾기
	@Override
	public String findPassword(String id, String name, String phone) throws Exception {
		return memberDAO.findPassword(id, name, phone);
	}

	// 비밀번호 변경
	@Override
	public void updatePassword(MemberDTO memberDTO) throws Exception {
		String encryptedPassword = encryptPassword(memberDTO.getPassword());
		memberDTO.setPassword(encryptedPassword);
		memberDAO.updatePassword(memberDTO);
	}

	// 암호화된 비밀번호 반환
	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	// 아이디 탈퇴
	@Override
	public void deleteMember(Long m_num) throws Exception {
		memberDAO.deleteMember(m_num);
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
