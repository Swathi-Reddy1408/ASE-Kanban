package com.kanban.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kanban.backend.Board;
import com.kanban.backend.Card;

public interface CardRepository extends JpaRepository<Card, Long> 
{

	/*@Query("UPDATE card SET title = :new_title WHERE id=:cid;")
    void  editcardTitle(@Param("title") String new_title,long cid);*/
}


