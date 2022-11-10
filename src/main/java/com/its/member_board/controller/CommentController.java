package com.its.member_board.controller;

import com.its.member_board.dto.CommentDTO;
import com.its.member_board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/comment/save")
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO){
        System.out.println("commentDTO 전 = " + commentDTO);
        commentService.save(commentDTO);
        List<CommentDTO> commentList = commentService.findAll(commentDTO.getBoardId());
        System.out.println("commentDTO 후 = " + commentDTO);
        return commentList;
    }
}
