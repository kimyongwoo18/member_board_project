package com.its.member_board.common;

public class PagingConst {
    //한 페이지에 보여줄 글 갯수
    public static final int PAGE_LIMIT = 5;
    // 하단에 보여줄 페이지 번호 갯수
    public static final int BLOCK_LIMIT =3;

    //static final > 상수를 정의할 때 이렇게 사용한다.
    //굳이 클래스로 구분하는 이유는 이클래스 저클래스에서 다 사용할 때 매번 변수로 지정하기 번거로우니
    //만약 상수값이 바뀌면 따로따로 변경하기 귀찮으니깐.
}
