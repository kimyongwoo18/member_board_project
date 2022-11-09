package com.its.member_board.service;

import com.its.member_board.dto.CommentDTO;
import com.its.member_board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
@Autowired
    CommentRepository commentRepository;


    public List<CommentDTO> findAll(long boardId) {
        return commentRepository.findAll(boardId);
    }
}
