package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResumeSearchVO {
//	임금형태, 장애유형, 중증여부, 지역, 학력
	private String[] payment_type;
	private String[] disease;
	private String[] ob_type;
	private String[] place;
	private String[] education;	
//	검색키워드
	private String keyword;
	
	@Builder
	public ResumeSearchVO(String[] payment_type, String[] disease, String[] ob_type, String[] place, String[] education,
			String keyword) {
		this.payment_type = payment_type;
		this.disease = disease;
		this.ob_type = ob_type;
		this.place = place;
		this.education = education;
		this.keyword = keyword;
	}
	
}
