package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WishVO {

	private Long w_id;
	private Long m_num;
	private Long j_num;
	
	@Builder
	public WishVO(Long w_id, Long m_num, Long j_num) {
		this.w_id = w_id;
		this.m_num = m_num;
		this.j_num = j_num;
	}
}