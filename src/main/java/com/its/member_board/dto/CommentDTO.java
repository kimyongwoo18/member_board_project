package com.its.member_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Setter
@Getter
@ToString

public class CommentDTO {
    private Long id;
    private String commentWriter ;
    private String commentContents;
    private Long boardId;
    private Timestamp commentCreatedDate;
}
