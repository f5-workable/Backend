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

import egovframework.job.dto.MemberDTO;
import egovframework.job.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/list")
    public String getMemberList(Model model) {
        List<MemberDTO> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);
        return "member/list";
    }

    @GetMapping("/member/add")
    public String addMember(Model model) {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "member/add";
    }
    
    @PostMapping("/member/add")
    public String addMember(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
        memberService.addMember(memberDTO);
        return "redirect:/member/list";
    }

    @GetMapping("/member/edit/{id}")
    public String editMember(@PathVariable("id") String id, Model model) {
        MemberDTO memberDTO = memberService.getMemberById(id);
        model.addAttribute("memberDTO", memberDTO);
        return "member/edit";
    }

    @PostMapping("/member/edit")
    public String editMember(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
        memberService.updateMember(memberDTO);
        return "redirect:/member/list";
    }

    @GetMapping("/member/delete")
    public String deleteMember(@RequestParam("id") String id) {
        memberService.deleteMember(id);
        return "redirect:/member/list";
    }
}