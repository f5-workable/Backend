package egovframework.job.dto;

import egovframework.job.vo.WishVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishDTO {

	private Long w_id;
	private Long m_num;
	private Long j_num;
	private boolean state;
	
	@Builder
    public WishDTO(final WishVO entity) {
    	this.w_id = entity.getW_id();
    	this.m_num = entity.getM_num();
    	this.j_num = entity.getJ_num();
    }
    
    public WishVO toEntity() {
    	return WishVO.builder()
    			.w_id(w_id)
    			.m_num(m_num)
    			.j_num(j_num)
    			.state(state)
    			.build();
    }
}
