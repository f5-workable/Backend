package egovframework.job.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobinfoSearchVO {
//	고용형태, 임금형태, 지역, 기업형태
	private String[] employment_type;
	private String[] payment_type;
	private String[] temp_address;
	private String[] tempcompany_type;
//	검색 키워드종류 : 업종, 기업명
	private String tempjob_type;
//	검색키워드
	private String keyword;
}
