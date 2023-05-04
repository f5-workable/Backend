package egovframework.job.dto;

import egovframework.job.domain.Wish;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishDTO {

	private Long w_id;
    private Long m_num;
    private Long j_num;

    @Builder
    public WishDTO(final Wish entity) {
    	this.w_id = entity.getW_id();
    	this.m_num = entity.getM_num();
    	this.j_num = entity.getJ_num();
    }
    
    public Wish toEntity() {
    	return Wish.builder()
    			.w_id(w_id)
    			.m_num(m_num)
    			.j_num(j_num)
    			.build();
    }
}
