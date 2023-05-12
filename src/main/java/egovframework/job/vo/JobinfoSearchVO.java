package egovframework.job.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobinfoSearchVO {
//   시작기간, 마감기간, 고용형태, 임금형태, 지역, 기업형태
   private String ds_date;
   private String de_date;
   private String[] employment_type;
   private String[] payment_type;
   private String[] region;
   
   private String search_type;
   private String keyword;
}