package com.its.member_board.controller;

import com.its.member_board.dto.MemberDTO;
import com.its.member_board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "memberSave";
    }
    @GetMapping("/memberCheck")
    public @ResponseBody MemberDTO  findByEmail(@RequestParam("memberEmail") String memberEmail){
        MemberDTO memberDTO = memberService.findByEmail(memberEmail);


          return memberDTO;
        }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO, Model model){
        memberService.save(memberDTO);

        return "index";
    }
}
