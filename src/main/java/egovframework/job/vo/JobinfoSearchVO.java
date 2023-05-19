package egovframework.job.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	
	@Builder
	public JobinfoSearchVO(String[] employment_type, String[] payment_type, String[] temp_address,
			String[] tempcompany_type, String tempjob_type, String keyword) {
		this.employment_type = employment_type;
		this.payment_type = payment_type;
		this.temp_address = temp_address;
		this.tempcompany_type = tempcompany_type;
		this.tempjob_type = tempjob_type;
		this.keyword = keyword;
	}
		
	
}
