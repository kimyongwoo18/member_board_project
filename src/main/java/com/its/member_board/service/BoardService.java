package com.its.member_board.service;

import com.its.member_board.common.PagingConst;
import com.its.member_board.dto.BoardDTO;
import com.its.member_board.dto.MemberDTO;
import com.its.member_board.dto.PageDTO;
import com.its.member_board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    public List<BoardDTO> findAll() {
        List<BoardDTO> result = boardRepository.findAll();
        return result;
    }

    public List<BoardDTO> pagingList(int page) {
        int pagingStart = (page-1) * PagingConst.PAGE_LIMIT;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", PagingConst.PAGE_LIMIT);
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams);
        return pagingList;
    }

    public PageDTO pagingParam(int page) {
        //전체 글 갯수 조회 >boardCount 에 전체 글 갯수 담겨옴.
        int boardCount = boardRepository.boardCount();
        // 전체 페이지 갯수 계산
        int maxPage = (int) (Math.ceil((double) boardCount / PagingConst.PAGE_LIMIT));
        // 시작 페이지 값 계산(1,4,7,10, ~~)
        int startPage = (((int)(Math.ceil((double) page / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        //끝 페이지 값 계산( 3, 6, 9, 12, ~~)
        int endPage = startPage + PagingConst.BLOCK_LIMIT - 1;
        if (endPage > maxPage){
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    public void updateHits(long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(long id) {
        return boardRepository.findById(id);
    }

    public void save(BoardDTO boardDTO) throws IOException {
        if(!boardDTO.getBoardFileName().isEmpty()) {
            System.out.println("파일있음");
            MultipartFile boardFile = boardDTO.getBoardFileName(); //1.
            String originalFilename = boardFile.getOriginalFilename(); //2.
            System.out.println("originalProfilename = " + originalFilename);
            System.out.println(System.currentTimeMillis());
            String storedFileName = System.currentTimeMillis() + "-" + originalFilename; //3.
            System.out.println("storedFileName = " + storedFileName);
            boardDTO.setOriginalFileName(originalFilename);
            boardDTO.setStoredFileName(storedFileName); //4.
            String savePath = "D:\\spring_img\\" + storedFileName; //5.
            boardFile.transferTo(new File(savePath));//6.
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.save(boardDTO); //7.
            boardRepository.saveFileName(savedBoard);
        } else {
            System.out.println("파일없음");
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        }
    }
}
