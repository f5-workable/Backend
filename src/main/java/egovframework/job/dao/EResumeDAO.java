package egovframework.job.dao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import egovframework.job.dto.EResumeDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class EResumeDAO {

    private final ElasticsearchClient elasticsearchClient;

    public EResumeDAO(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public Map<String, Object> searchResumes(String[] payment_type, String[] disease, String[] ob_type,
                                          String[] place, String[] education, String keyword, String sort,
                                          int pageNum, int pageSize) {
    	try {
            // Elasticsearch 검색 요청
            SearchResponse<EResumeDTO> response = elasticsearchClient.search(s -> s
                .index("resume_index")
                .query(q -> q
                    .bool(b -> {
                        // 조건 추가
                        if (payment_type != null && payment_type.length > 0) {
                            b.filter(f -> f
                                .terms(t -> t
                                    .field("payment_type.keyword")
                                    .terms(tq -> tq.value(
                                        Arrays.stream(payment_type)
                                            .map(FieldValue::of)
                                            .collect(Collectors.toList())
                                    ))
                                )
                            );
                        }
                        if (disease != null && disease.length > 0) {
                            b.filter(f -> f
                                .terms(t -> t
                                    .field("disease.keyword")
                                    .terms(tq -> tq.value(
                                        Arrays.stream(disease)
                                            .map(FieldValue::of)
                                            .collect(Collectors.toList())
                                    ))
                                )
                            );
                        }
                        if (ob_type != null && ob_type.length > 0) {
                            b.filter(f -> f
                                .terms(t -> t
                                    .field("ob_type.keyword")
                                    .terms(tq -> tq.value(
                                        Arrays.stream(ob_type)
                                            .map(FieldValue::of)
                                            .collect(Collectors.toList())
                                    ))
                                )
                            );
                        }
                        if (place != null && place.length > 0) {
                            b.filter(f -> f
                                .terms(t -> t
                                    .field("regions.keyword")
                                    .terms(tq -> tq.value(
                                        Arrays.stream(place)
                                            .map(FieldValue::of)
                                            .collect(Collectors.toList())
                                    ))
                                )
                            );
                        }
                        if (education != null && education.length > 0) {
                            b.filter(f -> f
                                .terms(t -> t
                                    .field("education.keyword")
                                    .terms(tq -> tq.value(
                                        Arrays.stream(education)
                                            .map(FieldValue::of)
                                            .collect(Collectors.toList())
                                    ))
                                )
                            );
                        }
                        if (keyword != null && !keyword.isEmpty()) {
                            b.must(m -> m
                                .matchPhrasePrefix(mp -> mp
                                    .field("job")
                                    .query(keyword)
                                )
                            );
                        }
                        return b;
                    })
                )
                .trackTotalHits(t -> t.enabled(true)) // 정확한 total 값을 가져오기 위해 설정
                .sort(so -> {
                    if ("최신순".equals(sort)) {
                        so.field(sf -> sf.field("r_id").order(SortOrder.Desc));
                    } else if ("희망임금순".equals(sort)) {
                        so.field(sf -> sf.field("payment").order(SortOrder.Asc));
                    }
                    return so;
                })
                .from((pageNum - 1) * pageSize)
                .size(pageSize),
                EResumeDTO.class);

            // 검색 결과 가공
            List<EResumeDTO> list = response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());

            // 결과 정보를 담을 LinkedHashMap 생성 (순서 보장)
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("total", response.hits().total().value()); // 전체 개수
            result.put("list", list); // 결과 데이터
            result.put("pageNum", pageNum); // 현재 페이지
            result.put("pageSize", pageSize); // 페이지 크기
            result.put("size", list.size()); // 현재 페이지 데이터 개수
            result.put("startRow", (pageNum - 1) * pageSize + 1); // 시작 데이터 인덱스
            result.put("endRow", (pageNum - 1) * pageSize + list.size()); // 끝 데이터 인덱스
            result.put("pages", (int) Math.ceil((double) response.hits().total().value() / pageSize)); // 총 페이지 수
            result.put("prePage", pageNum > 1 ? pageNum - 1 : 0); // 이전 페이지
            result.put("nextPage", pageNum < (int) Math.ceil((double) response.hits().total().value() / pageSize) ? pageNum + 1 : 0); // 다음 페이지
            result.put("isFirstPage", pageNum == 1); // 첫 페이지 여부
            result.put("isLastPage", pageNum == (int) Math.ceil((double) response.hits().total().value() / pageSize)); // 마지막 페이지 여부
            result.put("hasPreviousPage", pageNum > 1); // 이전 페이지 존재 여부
            result.put("hasNextPage", pageNum < (int) Math.ceil((double) response.hits().total().value() / pageSize)); // 다음 페이지 존재 여부
            result.put("navigatePages", 8); // 네비게이션에 표시할 페이지 수
            result.put("navigatepageNums", IntStream.rangeClosed(Math.max(1, pageNum - 4), Math.min((int) Math.ceil((double) response.hits().total().value() / pageSize), pageNum + 4))
                .boxed().collect(Collectors.toList())); // 네비게이션 페이지 번호

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
