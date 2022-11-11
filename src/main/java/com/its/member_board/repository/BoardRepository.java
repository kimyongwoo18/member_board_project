package com.its.member_board.repository;

import com.its.member_board.dto.BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    @Autowired
    SqlSessionTemplate sql;
    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingList", pagingParams);
    }

    public int boardCount() {
        return sql.selectOne("Board.boardCount");
    }

    public void updateHits(long id) {
        sql.update("Board.updateHits",id);
    }

    public BoardDTO findById(long id) {
        return sql.selectOne("Board.findById",id);
    }

    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save",boardDTO);
        return boardDTO;
    }

    public void saveFileName(BoardDTO boardDTO) {
        sql.insert("Board.saveFile", boardDTO);
    }

    public BoardDTO findByEmailMax(String boardWriter) {
        return sql.selectOne("Board.findByEmailMax", boardWriter);
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update",boardDTO);
    }

    public void delete(Long id) {
        sql.delete("Board.delete",id);
    }

    public List<BoardDTO> search(Map<String, String> searchParams) {
        return sql.selectList("Board.search", searchParams);
    }
}
