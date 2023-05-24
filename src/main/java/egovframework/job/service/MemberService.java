package egovframework.job.service;

import egovframework.job.dto.MemberDTO;

public interface MemberService {
	
	// 회원가입 처리
	public void registerMember(MemberDTO memberDTO) throws Exception;
	
	// 아이디 검색
	public MemberDTO findById(String id) throws Exception;
	
	// 아이디 상세정보
	public MemberDTO getMemberDetail(String id) throws Exception;
	
	// 아이디 상세정보 수정
	public void updateMemberDetail(MemberDTO memberDTO) throws Exception;
	
	// 아이디 탈퇴
	public void deleteMember(String id) throws Exception;
	
	// 대표이력서 등록
	public void rdefaultMember(MemberDTO dto);
}