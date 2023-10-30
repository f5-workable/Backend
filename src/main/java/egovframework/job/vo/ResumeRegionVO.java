package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResumeRegionVO {
	
	private Long region_id;
	private Long r_id;
	private String region;
	
	@Builder
	public ResumeRegionVO(Long region_id, Long r_id, String region) {
		this.region_id = region_id;
		this.r_id = r_id;
		this.region = region;
	}
}