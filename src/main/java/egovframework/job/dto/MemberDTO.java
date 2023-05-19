package egovframework.job.dto;

import java.util.List;
import java.util.stream.Collectors;

import egovframework.job.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

	// 멤버 테이블 시퀀스 번호 (PK)
	private Long m_num;

	// 멤버 아이디
	private String id;

	// 멤버 비밀번호
	private String password;
	
	// 멤버 생년월일
	private String birth;

	// 멤버 이름
	private String name;
	
	// 멤버 성별
	private String gender;

	// 멤버 이메일
	private String email;

	// 멤버 전화번호
	private String phone;

	// 멤버 프로필 경로
	private String profil;

	// 멤버 구분
	@Builder.Default
	private String role = "ROLE_MEMBER";

	// 멤버 탈퇴 여부
	private boolean deleted;

	
	// MemberVO 객체를 MemberDTO로 변환
	public static MemberDTO of(MemberVO entity) {
		return MemberDTO.builder()
				.m_num(entity.getM_num())
				.id(entity.getId())
				.password(entity.getPassword())
				.birth(entity.getBirth())
				.name(entity.getName())
				.gender(entity.getGender())
				.email(entity.getEmail())
				.phone(entity.getPhone())
				.profil(entity.getProfil())
				.role(entity.getRole())
				.deleted(entity.isDeleted())
				.build();
	}

	
	// MemberVO 객체 리스트를 MemberDTO 객체 리스트로 변환
	public static List<MemberDTO> of(List<MemberVO> entityList) {
		return entityList.stream()
				.map(MemberDTO::of)
				.collect(Collectors.toList());
	}

	
	// MemberDTO 객체를 MemberVO로 변환
	public MemberVO toEntity() {
		return MemberVO.builder()
				.m_num(m_num)
				.id(id)
				.password(password)
				.birth(birth)
				.name(name)
				.gender(gender)
				.email(email)
				.phone(phone)
				.profil(profil)
				.role(role)
				.deleted(deleted)
				.build();
	}
}