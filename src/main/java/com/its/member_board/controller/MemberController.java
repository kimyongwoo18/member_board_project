package com.its.member_board.controller;

import com.its.member_board.dto.MemberDTO;
import com.its.member_board.dto.PageDTO;
import com.its.member_board.service.MemberService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    SqlSessionTemplate session;
    @GetMapping("/save")
    public String saveForm(){
        return "memberSave";
    }
    @PostMapping("/memberCheck")
    public @ResponseBody String findByEmail(@RequestParam("memberEmail") String memberEmail){
        MemberDTO memberDTO = memberService.findByEmail(memberEmail);

        System.out.println("memberDTO = " + memberDTO);

        if(memberDTO == null){
            return "ok";
        }else{
            return "no";
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        System.out.println("memberDTO = " + memberDTO);

        memberService.save(memberDTO);

        return "index";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "memberLogin";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        boolean loginResult = memberService.login(memberDTO);
        if(loginResult == true){
            session.setAttribute("loginName", memberDTO.getMemberName());
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            if(memberDTO.getMemberEmail() == "admin"){
                return "admin";
            }else{
                return "boardList";
            }
        }else{
            return "index";
        }
    }
    @GetMapping("/member/")
    public String findAll(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "1")int page){
       List<MemberDTO> memberList = memberService.findAll(page);
    //해당 페이지에서 보여줄 글 목록
        // 하단 페이지 번호 표현을 위한 데이터
        PageDTO pageDTO = memberService.pagingParam(page);
        model.addAttribute("memberList", memberList);
        model.addAttribute("paging", pageDTO);

        return "memberList";
    }
    @GetMapping("/memberMain")
    public String memberMain(){
        return "memberMain";
    }
    @GetMapping("/member/update")
    public String updateForm(@RequestParam("memberEmail") String memberEmail,
                         Model model){
        MemberDTO memberResult = memberService.findByEmail(memberEmail);
        model.addAttribute("member", memberResult);
        return "memberUpdate";
    }
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO,HttpSession session,Model model) throws IOException {
        memberService.update(memberDTO);
        session.invalidate();
        return "index";
    }
}
