package com.kanban.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kanban.backend.Board;
import com.kanban.backend.repository.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board create(Board board){
    	
        return boardRepository.save(board);
    }
    public void deleteBoard(Long bid)
    {
    	boardRepository.deleteById(bid);
    }
}


