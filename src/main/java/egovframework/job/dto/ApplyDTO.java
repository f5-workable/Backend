package egovframework.job.dto;

import egovframework.job.domain.Apply;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyDTO {
	private Long a_id;
	
	private String state;
	private Long j_num;
	private Long cr_num;
	
	@Builder
	public ApplyDTO(final Apply entity) {
		this.a_id = entity.getA_id();
		this.state = entity.getState();
		this.j_num = entity.getJ_num();
		this.cr_num = entity.getCr_num();
	}
	
	public Apply toEntity() {
		return Apply.builder()
				.a_id(a_id)
				.state(state)
				.j_num(j_num)
				.cr_num(cr_num)
				.build();
	}
}
