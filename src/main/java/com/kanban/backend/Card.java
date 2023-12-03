package com.kanban.backend;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="card")
//Card.java
public class Card {
 @Id
 private Long id;

 public Long getId() {
	return id;
}
public String getTitle() {
	return title;
}
public List<String> getTasks() {
	return tasks;
}
public List<String> getLabels() {
	return labels;
}
public String getDate() {
	return date;
}
public Board getBoard() {
	return board;
}

private String title;

 @ElementCollection
 private List<String> tasks = new ArrayList<>();

 @ElementCollection
 private List<String> labels = new ArrayList<>();

 private String date;

 // Many-to-One relationship with Board
 @ManyToOne
 @JoinColumn(name = "board_id")
 private Board board;

 public void setId(long id) {
     this.id = id;
 }
 public void setTitle(String title) {
     this.title = title;
 }

 public void setTasks(List<String> tasks) {
     if (tasks != null) {
         this.tasks.clear();
         this.tasks.addAll(tasks);
     }
 }

 public void setLabels(List<String> labels) {
     if (labels != null) {
         this.labels.clear();
         this.labels.addAll(labels);
     }
 }

 public void setDate(String date) {
     this.date = date;
 }

 public void setBoard(Board board) {
     this.board = board;
 }

 // Other fields, getters, setters
}


