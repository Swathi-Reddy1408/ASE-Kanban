package com.kanban.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kanban.backend.Board;
import com.kanban.backend.service.BoardService;
import com.kanban.backend.service.CardService;

public class CardController {


	@Autowired
    private BoardService boardService;
	
	@Autowired
    private CardService cardService;

	 @CrossOrigin
    @PostMapping("/boards/addCard")
    
    public ResponseEntity<String> addCardToBoard(
            @RequestParam Long boardId,
            @RequestParam Long cardId,
            @RequestParam String cardTitle,
            @RequestParam List<String> tasks,
            @RequestParam List<String> labels,
            @RequestParam String date
    )
    {
    	System.out.println("I am heere and boardid " +boardId);
        cardService.addCardToBoard(boardId,cardId, cardTitle, tasks, labels, date);
        return ResponseEntity.ok("Card added to the board successfully");
    }

}
