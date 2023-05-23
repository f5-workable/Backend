package egovframework.job.vo;

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
	private String[] address;
	private String[] c_type;
//	검색 키워드종류 : 업종, 기업명
	private String job_type;
//	검색키워드
	private String keyword;
//	memberId
	private Long user_id;
	@Builder
	public JobinfoSearchVO(String[] employment_type, String[] payment_type, String[] address,
			String[] c_type, String job_type, String keyword) {
		this.employment_type = employment_type;
		this.payment_type = payment_type;
		this.address = address;
		this.c_type = c_type;
		this.job_type = job_type;
		this.keyword = keyword;
	}
	
}
