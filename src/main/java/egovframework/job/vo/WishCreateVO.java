package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishCreateVO {
	
	private Long m_num;
	private Long j_num;
	
	@Builder
	public WishCreateVO(Long m_num, Long j_num) {
		this.m_num = m_num;
		this.j_num = j_num;
	}
}
