package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplyVO {
    private Long a_id;
	private String state;
	private Long j_num;
	private Long cr_num;
}