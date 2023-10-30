package egovframework.job.dto;

import egovframework.job.vo.WishCreateVO;
import egovframework.job.vo.WishVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishDTO {

	private Long w_id;
	private Long m_num;
	private Long j_num;
	
	@Builder
	public WishDTO(Long m_num, Long j_num) {
		this.m_num = m_num;
		this.j_num = j_num;
	}
}
