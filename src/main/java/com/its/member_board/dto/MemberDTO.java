package com.its.member_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
    //사진을 담기위한 필드
    private MultipartFile memberProfile;
    // 원본사진 이름용 필드
    private String originalFileName;
    // 서버관리 이름용 필드
    private String storedFileName;
    //사진이 있나없나 확인하는 필드 0이면 없음 1이면 있음.
    private int profileAttached;

}
