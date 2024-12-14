package egovframework.job.web;

import egovframework.job.dto.EResumeDTO;
import egovframework.job.service.EResumeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
public class EResumeController {

    private final EResumeService resumeService;

    public EResumeController(EResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/search")
    public Map<String, Object> searchResumes(
        @RequestParam(value = "payment_type", required = false, defaultValue = "") String[] payment_type,
        @RequestParam(value = "disease", required = false, defaultValue = "") String[] disease,
        @RequestParam(value = "ob_type", required = false, defaultValue = "") String[] ob_type,
        @RequestParam(value = "place", required = false, defaultValue = "") String[] place,
        @RequestParam(value = "education", required = false, defaultValue = "") String[] education,
        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
        @RequestParam(value = "sort", required = false, defaultValue = "최신순") String sort,
        @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", required = false, defaultValue = "12") int pageSize) {
        return resumeService.searchResumes(payment_type, disease, ob_type, place, education, keyword, sort, pageNum, pageSize);
    }
}
