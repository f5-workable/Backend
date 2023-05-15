package egovframework.job.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeSearchVO {
//	임금형태, 장애유형, 중증여부, 지역
	private String[] payment_type;
	private String[] disease;
	private String[] ob_type;
	private String[] place;
//	검색키워드
	private String keyword;
}
