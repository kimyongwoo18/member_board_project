package com.its.member_board.repository;

import com.its.member_board.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @Autowired
    SqlSessionTemplate sql;


    public MemberDTO findByEmail(String memberEmail) {
        return sql.selectOne("Member.findByEmail", memberEmail);
    }

    public void save(MemberDTO memberDTO) {
        sql.insert("Member.save", memberDTO);
    }
}
