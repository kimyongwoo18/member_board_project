package com.its.member_board.repository;

import com.its.member_board.dto.CommentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    @Autowired
    SqlSessionTemplate sql;
    public List<CommentDTO> findAll(long boardId) {
        return sql.selectList("Comment.findAll",boardId);
    }
}
