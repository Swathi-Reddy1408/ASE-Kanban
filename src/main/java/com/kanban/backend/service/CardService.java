package com.kanban.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kanban.backend.Board;
import com.kanban.backend.Card;
import com.kanban.backend.repository.BoardRepository;
import com.kanban.backend.repository.CardRepository;

@Service
public class CardService {
	    @Autowired
	    private BoardRepository boardRepository;

	    @Autowired
	    private CardRepository cardRepository;

	    public void addCardToBoard(Long boardId, Long id, String cardTitle, List<String> tasks, List<String> labels, String date) {
	    	Board board = boardRepository.findById(boardId)
	    	        .orElseThrow();
	        Card card = new Card();
	        card.setId(id);
	        card.setTitle(cardTitle);
	        card.setTasks(tasks);
	        card.setLabels(labels);
	        card.setDate(date);
	        card.setBoard(board);

	        cardRepository.save(card);
	    }
	    public void deleteCardFromBoard(Long id)
	    {
	    	cardRepository.deleteById(id);
	    }
	    /*public void editCard(String title,long id)
	    {
	    	cardRepository.editcardTitle(title,id);
	    }*/

	}


