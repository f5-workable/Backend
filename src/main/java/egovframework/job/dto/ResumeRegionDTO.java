package egovframework.job.dto;

import egovframework.job.vo.ResumeRegionVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResumeRegionDTO {
	
	private Long region_id;
	private Long r_id;
	private String region;
	
	@Builder
	public ResumeRegionDTO(Long r_id, String region) {
		this.r_id = r_id;
		this.region = region;
	}
	
//	public ResumeRegionVO toEntity() {
//		return ResumeRegionVO.builder()
//				.region_id(region_id)
//				.r_id(r_id)
//				.region(region)
//				.build();
//	}
}