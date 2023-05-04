package egovframework.job.service;

import org.springframework.stereotype.Service;

import egovframework.job.domain.Jobinfo;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.repository.JobinfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobinfoService {
	private final JobinfoRepository jobinfoRepository;
	
	//Create
	public void add(JobinfoDTO dto) {
		Jobinfo info = dto.toEntity();
		jobinfoRepository.save(info);
	}
	//Read
	
	//Update
	public void update(JobinfoDTO dto) {
		//DirtyChecking
	}
	
	//Delete
	public void delete(JobinfoDTO dto) {
		Long id = dto.getJ_id();
		jobinfoRepository.delete(id);
	}
}
