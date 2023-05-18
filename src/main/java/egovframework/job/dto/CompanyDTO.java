package egovframework.job.dto;

import java.util.List;
import java.util.stream.Collectors;

import egovframework.job.vo.CompanyVO;
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
public class CompanyDTO {
	
	// 기업 테이블 시퀀스 번호 (PK)
	private Long c_num;

	// 기업 아이디
	private String c_id;

	// 기업 비밀번호
	private String c_password;

	// 기업 이름
	private String c_name;

	// 기업 사업자 번호
	private String rnum;

	// 기업 주소
	private String address;

	// 기업 형태
	private String c_type;

	// 기업 연락처
	private String phone;

	// 기업 로고
	private String logo;

	// 기업 구분
	private String role;

	
	// CompanyVO 객체를 CompanyDTO로 변환
	public static CompanyDTO of(CompanyVO entity) {
		return CompanyDTO.builder()
				.c_id(entity.getC_id())
				.c_password(entity.getC_password())
				.c_name(entity.getC_name())
				.rnum(entity.getRnum())
				.address(entity.getAddress())
				.c_type(entity.getC_type())
				.phone(entity.getPhone())
				.logo(entity.getLogo())
				.role(entity.getRole())
				.build();
	}

	// CompanyVO 객체 리스트를 CompanyDTO 객체 리스트로 변환
	public static List<CompanyDTO> of(List<CompanyVO> entityList) {
		return entityList.stream()
				.map(CompanyDTO::of)
				.collect(Collectors.toList());
	}

	// CompanyDTO 객체를 CompanyVO로 변환
	public CompanyVO toEntity() {
		return CompanyVO.builder()
				.c_id(c_id)
				.c_password(c_password)
				.c_name(c_name)
				.rnum(rnum)
				.address(address)
				.c_type(c_type)
				.phone(phone)
				.logo(logo)
				.role(role)
				.build();
	}
}