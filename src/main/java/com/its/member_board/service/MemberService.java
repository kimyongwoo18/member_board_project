package com.its.member_board.service;

import com.its.member_board.dto.MemberDTO;
import com.its.member_board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public MemberDTO findByEmail(String memberEmail) {

        MemberDTO memberDTO = memberRepository.findByEmail(memberEmail);

        return memberDTO;
    }

    public void save(MemberDTO memberDTO) {
        memberRepository.save(memberDTO);
    }
}
