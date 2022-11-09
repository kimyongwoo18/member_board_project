package com.its.member_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
    private int page; //현재 페이지
    private int maxPage; // 전체 페이지
    private int startPage; // 시작 페이지
    private int endPage; // 끝 페이지
}
