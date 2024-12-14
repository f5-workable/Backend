package egovframework.job.service;

import egovframework.job.dao.EResumeDAO;
import egovframework.job.dto.EResumeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EResumeService {

    private final EResumeDAO resumeDAO;

    public EResumeService(EResumeDAO resumeDAO) {
        this.resumeDAO = resumeDAO;
    }

    public Map<String, Object> searchResumes(String[] payment_type, String[] disease, String[] ob_type,
                                         String[] place, String[] education, String keyword, String sort,
                                         int pageNum, int pageSize) {
        return resumeDAO.searchResumes(payment_type, disease, ob_type, place, education, keyword, sort, pageNum, pageSize);
    }
}
