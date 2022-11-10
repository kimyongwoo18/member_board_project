package com.its.member_board.controller;

import com.its.member_board.dto.CommentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {
    @PostMapping("/comment/save")
    public @ResponseBody List<CommentDTO> Save(@ModelAttribute CommentDTO commentDTO){

        commentService.save(commentDTO);
        List<CommentDTO> commentList = commentService.findAll(commentDTO.getBoardId());
        return commentList;
    }
}
