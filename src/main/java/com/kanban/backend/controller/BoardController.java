package com.kanban.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kanban.backend.Board;
import com.kanban.backend.Card;
import com.kanban.backend.service.BoardService;
import com.kanban.backend.service.CardService;
import com.kanban.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
public class BoardController {
	
        @Autowired
        private BoardService boardService;
        @Autowired
        private CardService cardService;
        @Autowired
        private UserService userService;



    @CrossOrigin
    @PostMapping("/board")
    public ResponseEntity<?> save(@RequestBody Board board){
       // System.out.println(board);

        System.out.println("id " + board.getId());
        System.out.println("Title " + board.getTitle());
        return new ResponseEntity<>(boardService.create(board), HttpStatus.CREATED);
    
    }
    @CrossOrigin
    @GetMapping("/board")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(boardService.getAllBoards(), HttpStatus.OK);
    }
    
    
    
    @CrossOrigin
    @RequestMapping("/board/{boardId}/addCard")
    public ResponseEntity<?> addCardToBoard(
    		@PathVariable Long boardId,
            @RequestBody Card card
            /*Long cardId,
            @RequestParam String cardTitle,
            @RequestParam List<String> tasks,
            @RequestParam List<String> labels,
            @RequestParam String date*/
    )
    {
    	//System.out.println("I am heere and boardid " +boardId);
    	//System.out.println(card.getId());
        cardService.addCardToBoard(boardId,card.getId(), card.getTitle(), card.getTasks(), card.getLabels(), card.getDate());
        return ResponseEntity.ok("Card added to the board successfully");
    }
    @CrossOrigin
    @DeleteMapping("/board/deleteCard")
    public ResponseEntity<?> deleteCardFromBoard(@RequestBody Map<String, Long> cardData)
    {
    	Long cid=cardData.get("cid");
    	//System.out.println(cid);
    	cardService.deleteCardFromBoard(cid);
        return ResponseEntity.ok("Card deleted from the board successfully");

    }
    @CrossOrigin
    @DeleteMapping("/board/deleteBoard")
    public ResponseEntity<?> deleteBoard(@RequestBody Map<String, Long> cardData)
    {
    	Long bid=cardData.get("bid");
    	//System.out.println(cid);
    	boardService.deleteBoard(bid);
        return ResponseEntity.ok(" Board deleted successfully");

    }
    @CrossOrigin
    @PostMapping("/Login")
    public ResponseEntity<?> Login(@RequestBody Map<String, String> userData) {
    	String email=userData.get("email");
    	String password=userData.get("password");
        
    	//System.out.println(email);
    	//System.out.println(password);
    	//System.out.println("I am here");
    	String isUsernameOrEmailValid = userService.findByUsernameOrEmail(email, password);

        if (isUsernameOrEmailValid != null) {
            String body=isUsernameOrEmailValid;
            System.out.println(body);
            return ResponseEntity.status(HttpStatus.OK)
            	    .body(body);
        } else {
        	System.out.println("Invalid user email or Password");
        	return ResponseEntity.status(HttpStatus.OK)
        		    .body("Invalid User email or Password"); // Or handle as needed
        }
    	
        
    }
    /*@CrossOrigin
    @PostMapping("/EditCardTitle")
    public ResponseEntity<?> EditCardTitle(@RequestBody Map<String, String> cardData) {
    	String card_title=cardData.get("title");
    	long cid=cardData.get("id");
        
    	System.out.println(card_title);
    	//System.out.println(password);
    	//System.out.println("I am here");
    	String isUsernameOrEmailValid = userService.findByUsernameOrEmail(email, password);

        if (isUsernameOrEmailValid != null) {
            String body=isUsernameOrEmailValid;
            System.out.println(body);
            return ResponseEntity.status(HttpStatus.OK)
            	    .body(body);
        } else {
        	System.out.println("Invalid user email or Password");
        	return ResponseEntity.status(HttpStatus.OK)
        		    .body("Invalid User email or Password"); // Or handle as needed
        }
    	
        
    }*/
    
    
}
