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
            //여기에는 어차피 저장한 값이 올라가니까 돌아오는것은 특정한 값일것이다.
            //올린 글 중에 가장 최신일것이므로 maxid값을 가져와서 그 값을 데이터로 넣는다?
            boardRepository.save(boardDTO); //7.
//            저장하고 돌아온 데이터중에서 id값을 추출하고 그 아이디값을 boardId에 넣고 보드데이터 저장.
            BoardDTO recentBoard =boardRepository.findByEmailMax(boardDTO.getBoardWriter());
            //특정 아이디를 찾기위해서 해당 이메일로 저장된 값중에 가장 최신의 글의id값을 가져온다.(id값만 가져오면 되는데 굳이 다 가져오긴했다)
            boardDTO.setId(recentBoard.getId());
            boardRepository.saveFileName(boardDTO);
        } else {
            System.out.println("파일없음");
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);
        }
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public List<BoardDTO> search(String type, String q) {
        Map<String, String> searchParams = new HashMap<>();
        searchParams.put("type",type);
        searchParams.put("q",q);
        List<BoardDTO> searchList = boardRepository.search(searchParams);
        return searchList;

    }
}
