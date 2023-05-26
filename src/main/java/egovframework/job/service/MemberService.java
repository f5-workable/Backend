package egovframework.job.service;

import egovframework.job.dto.MemberDTO;

public interface MemberService {

	// 회원가입 처리
	public void registerMember(MemberDTO memberDTO) throws Exception;

	// 아이디 중복 검색
	public boolean isIdDuplicate(String id) throws Exception;

	// 아이디 검색
	public MemberDTO findById(String id) throws Exception;

	// 시퀀스 번호로 멤버 조회
	public MemberDTO findByMNum(Long m_num) throws Exception;

	// 아이디 상세정보
	public MemberDTO getMemberDetail(String id) throws Exception;

	// 아이디 상세정보 수정
	public void updateMemberDetail(MemberDTO memberDTO) throws Exception;

	// 아이디 상세정보 수정
	public void updateSequenceMemberDetail(MemberDTO memberDTO) throws Exception;

	// 비밀번호 찾기
	public String findPassword(String id, String name, String phone) throws Exception;

	// 비밀번호 변경
	void updatePassword(MemberDTO memberDTO) throws Exception;

	// 아이디 탈퇴
	public void deleteMember(String id) throws Exception;

	// 대표이력서 등록
	public void rdefaultMember(MemberDTO dto);

	// 시퀀스아이디로 검색
	public MemberDTO findByLongId(Long id) throws Exception;
}