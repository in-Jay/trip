package com.example.trip.controller;

import com.example.trip.dto.Board;
import com.example.trip.dto.BoardForm;
import com.example.trip.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시글 목록 가져오기
    @GetMapping("/list")
    public String getAllBoard(Model model) {
        List<Board> boardList = boardService.getAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    //게시글 작성하기
    @GetMapping("/write")
    public String createBoard(BoardForm boardForm) {
        return "board/write";
    }
    @PostMapping("/write")
    public String createBoard(@Valid BoardForm boardForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        boardService.writeBoard(boardForm.getTitle(), boardForm.getContent());
        return "redirect:/board/list";
    }

    //게시글 상세 조회
    @GetMapping("/detail/{id}")
    public String getBoardById(Model model, @PathVariable("id") Long id) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    //게시글 수정하기
    @GetMapping("/modify/{id}")
    public String modifyBoard(BoardForm boardForm, @PathVariable("id") Long id) {
        Board board = boardService.getBoard(id);
        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        return "board/write";
    }
    @PostMapping("/modify/{id}")
    public String modifyBoard(@Valid BoardForm boardForm, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        Board board = boardService.getBoard(id);
        boardService.modifyBoard(board, boardForm.getTitle(), boardForm.getContent());
        return String.format("redirect:/board/detail/%s, id");
    }

    //게시글 삭제하기
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        Board board = boardService.getBoard(id);
        boardService.deleteBoard(board);
        return "redirect:/board/list";
    }
}
