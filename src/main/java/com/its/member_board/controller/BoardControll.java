package com.its.member_board.controller;

import com.its.member_board.dto.BoardDTO;
import com.its.member_board.dto.CommentDTO;
import com.its.member_board.dto.PageDTO;
import com.its.member_board.repository.BoardRepository;
import com.its.member_board.service.BoardService;
import com.its.member_board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class BoardControll {
    @Autowired
    private BoardService boardService;
    private CommentService commentService;

    @GetMapping("/board/")
    public String findAll(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "1")int page){

        //해당 페이지에서 보여줄 글 목록
        List<BoardDTO> pagingList = boardService.pagingList(page);
        // 하단 페이지 번호 표현을 위한 데이터
        PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return"boardList";
    }
    @GetMapping("/board")
    public String findById(@RequestParam("id") long id, Model model,
                           @RequestParam(value= "page", required = false, defaultValue = "1") int page){
        //조회수 증가
        boardService.updateHits(id);
        //상세내용 가져오기
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page",page);
        System.out.println("조회 : boardDTO = " + boardDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        return "boardPages/boardDetail";
    }
    @GetMapping("/boardSave")
    public String saveForm(){
        return "boardSave";
    }
    @PostMapping("/boardSave")
    public String save(@ModelAttribute BoardDTO boardDTO,Model model) throws IOException {
        boardService.save(boardDTO);
        return"redirect:/board/";
    }
}
