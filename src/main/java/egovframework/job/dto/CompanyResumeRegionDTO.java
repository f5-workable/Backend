package egovframework.job.dto;

import egovframework.job.vo.CompanyResumeRegionVO;
import egovframework.job.vo.ResumeRegionVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResumeRegionDTO {

	private long cr_region_id;
	private long cr_num;
	private String region;
	
    @Builder
    public CompanyResumeRegionDTO(CompanyResumeRegionVO entity) {
    	this.cr_region_id = entity.getCr_region_id();
    	this.cr_num =entity.getCr_num();
    	this.region = entity.getRegion();
    }
    
    
    public CompanyResumeRegionVO toEntity() {
    	return CompanyResumeRegionVO.builder()
    			.cr_region_id(cr_region_id)
    			.cr_num(cr_num)
    			.region(region)
    			.build();
    }

}
