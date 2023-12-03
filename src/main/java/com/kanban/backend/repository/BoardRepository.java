package com.kanban.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kanban.backend.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    

    
} 