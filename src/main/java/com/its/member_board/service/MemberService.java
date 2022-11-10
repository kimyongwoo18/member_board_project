package com.its.member_board.service;

import com.its.member_board.common.PagingConst;

import com.its.member_board.dto.MemberDTO;
import com.its.member_board.dto.PageDTO;
import com.its.member_board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public MemberDTO findByEmail(String memberEmail) {

        MemberDTO memberDTO = memberRepository.findByEmail(memberEmail);

        return memberDTO;
    }

    public void save(MemberDTO memberDTO) throws IOException {
               /*
             1. MemberDTO 객체에 담긴 파일을 꺼냄
             2. 파일의 원본 이름을 가져옴.(originalFileName)
             3. 서버 관리용 이름을 만듦. (storedFileName)
             4. originalFileName, storedFileName을 dto객체에 담음.
             5. 파일 실제 저장 위치 지정.
             6.파일 저장 처리
             7. repository로 dto 객체 전달.
        */
        if(!memberDTO.getMemberProfile().isEmpty()) {
            System.out.println("파일있음");
            MultipartFile memberFile = memberDTO.getMemberProfile(); //1.
            String originalFilename = memberFile.getOriginalFilename(); //2.
            System.out.println("originalFilename = " + originalFilename);
            System.out.println(System.currentTimeMillis());
            String storedFileName = System.currentTimeMillis() + "-" + originalFilename; //3.
            System.out.println("storedFileName = " + storedFileName);
            memberDTO.setOriginalFileName(originalFilename);
            memberDTO.setStoredFileName(storedFileName); //4.
            String savePath = "D:\\spring_img\\" + storedFileName; //5.
            memberFile.transferTo(new File(savePath));//6.
            memberDTO.setProfileAttached(1);
            memberRepository.save(memberDTO); //7.
            MemberDTO member = memberRepository.findByEmail(memberDTO.getMemberEmail());
            memberDTO.setId(member.getId());
            memberRepository.saveProfileName(memberDTO);
        } else {
            System.out.println("파일없음");
            memberDTO.setProfileAttached(0);
            memberRepository.save(memberDTO);
        }
    }

    public boolean login(MemberDTO memberDTO) {
        MemberDTO result = memberRepository.login(memberDTO);
        if(result != null){
            return true;
        }else{
            return false;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberDTO> result = memberRepository.findAll();
        return result;
    }

    public PageDTO pagingParam(int page) {
        //전체 글 갯수 조회 >boardCount 에 전체 글 갯수 담겨옴.
        int memberCount = memberRepository.memberCount();
        // 전체 페이지 갯수 계산
        int maxPage = (int) (Math.ceil((double) memberCount / PagingConst.PAGE_LIMIT));
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

    public void update(MemberDTO memberDTO) throws IOException {
        if(!memberDTO.getMemberProfile().isEmpty()) {
            System.out.println("파일있음");
            MultipartFile memberFile = memberDTO.getMemberProfile(); //1.
            String originalFilename = memberFile.getOriginalFilename(); //2.
            System.out.println("originalFilename = " + originalFilename);
            System.out.println(System.currentTimeMillis());
            String storedFileName = System.currentTimeMillis() + "-" + originalFilename; //3.
            System.out.println("storedFileName = " + storedFileName);
            memberDTO.setOriginalFileName(originalFilename);
            memberDTO.setStoredFileName(storedFileName); //4.
            String savePath = "D:\\spring_img\\" + storedFileName; //5.
            memberFile.transferTo(new File(savePath));//6.
            memberDTO.setProfileAttached(1);
            memberRepository.update(memberDTO); //7.
            memberRepository.updateProfileName(memberDTO);
        } else {
            System.out.println("파일없음");
            memberDTO.setProfileAttached(0);
            memberRepository.update(memberDTO);
        }

    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public List<MemberDTO> findFile() {
         List<MemberDTO> memberFile = memberRepository.findFile();
        return memberFile;
    }

    public List<MemberDTO> pagingList(int page) {
        int pagingStart = (page-1) * PagingConst.PAGE_LIMIT;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", PagingConst.PAGE_LIMIT);
        List<MemberDTO> pagingList = memberRepository.pagingList(pagingParams);
        return pagingList;
    }
}
