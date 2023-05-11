package egovframework.job.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.job.dto.CompanyDTO;
import egovframework.job.service.CompanyService;
import egovframework.job.vo.CompanyVO;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/*
	 * // Company 리스트 조회
	 * 
	 * @GetMapping("/company") public ResponseEntity<List<CompanyVO>>
	 * selectCompany() { List<CompanyVO> res = companyService.getCompanyList();
	 * 
	 * return ResponseEntity.ok(res); }
	 * 
	 * // id별(기본키) 조회
	 * 
	 * @GetMapping("/company/{id}") public ResponseEntity<CompanyVO>
	 * selectOne(@PathVariable String id) { CompanyVO res =
	 * companyService.getCompanyById(id); return ResponseEntity.ok(res); }
	 */
	
	@GetMapping("/company/list")
    public String getCompanyList(Model model) {
        //List<CompanyVO> companyList = companyService.getCompanyList();
        //model.addAttribute("companyList", companyList);
        
        return "company/list";
    }
	
	@GetMapping("/company/add")
    public String addCompany(Model model) {
        CompanyDTO companyDTO = new CompanyDTO();
        model.addAttribute("companyDTO", companyDTO);
        
        return "company/add";
    }
	
	@PostMapping("/company/add")
    public String addCompany(@ModelAttribute("companyDTO") CompanyDTO companyDTO) {
		companyService.addCompany(companyDTO);
		
        return "redirect:/company/list";
    }
	
	@GetMapping("/company/edit/{id}")
	public String editCompany(@PathVariable("id") String id, Model model) {
		CompanyVO companyVO = companyService.getCompanyById(id);
		model.addAttribute("companyVO", companyVO);
		
		return "company/edit";
	}

	@PostMapping("/company/edit")
	public String editCompany(@ModelAttribute("CompanyVO") CompanyDTO companyDTO) {
		companyService.updateCompany(companyDTO);
		
		return "redirect:/company/list";
	}
	
	@GetMapping("/company/delete")
	public String deleteCompany(@RequestParam("id") String id) {
		companyService.deleteCompany(id); 
		
		return "redirect:/company/list";
	}
}
