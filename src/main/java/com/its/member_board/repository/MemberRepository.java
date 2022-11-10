package com.its.member_board.repository;

import com.its.member_board.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    @Autowired
    SqlSessionTemplate sql;


    public MemberDTO findByEmail(String memberEmail) {
        return sql.selectOne("Member.findByEmail", memberEmail);
    }

    public MemberDTO save(MemberDTO memberDTO) {
        sql.insert("Member.save", memberDTO);
        return memberDTO;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }

    public void saveProfileName(MemberDTO memberDTO) {
        sql.insert("Member.saveProfile", memberDTO);
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }

    public int memberCount() {
        return sql.selectOne("Member.memberCount");
    }

    public void update(MemberDTO memberDTO) {
        sql.update("Member.update", memberDTO);
    }

    public void updateProfileName(MemberDTO memberDTO) {
        sql.update("Member,updateProfile",memberDTO);
    }

    public void delete(Long id) {
        sql.delete("Member.delete",id);
    }

    public List<MemberDTO> findFile() {
        return sql.selectList("Member.findFile");
    }

    public List<MemberDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Member.pagingList", pagingParams);
    }
}
