package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobinfoSelectVO {
	
	private Long j_id;
	private Long memberId;
	
	@Builder
	public JobinfoSelectVO(Long j_id, Long memberId) {
		this.j_id = j_id;
		this.memberId = memberId;
	}
}
