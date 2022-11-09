package com.its.member_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private Timestamp boardCreatedDate;
    // 파일이 있나없나 확인하는 필드 0이면 없음 1이면 있음.
    private int fileAttached;
    //파일을 담기위한 필드
    private MultipartFile boardFileName;
    // 원본파일 이름용 필드
    private String originalFileName;
    // 서버관리 이름용 필드
    private String storedFileName;


}
