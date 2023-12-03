
import React, { useEffect, useState } from "react";

import Board from "./Components/Board/Board";

import { faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useLocation } from 'react-router-dom';
import "./App.css";
import Editable from "./Components/Editabled/Editable";





function App({history}) {

const location = useLocation();
const responseData = location.state && location.state.responseData;
const username = responseData.split("@")[0];
// Now you can use responseData in your component



  const [boards, setBoards] = useState(
    JSON.parse(localStorage.getItem("prac-kanban")) || []
  );

  const [targetCard, setTargetCard] = useState({
    bid: "",
    cid: "",
  });
  const [showUserInfo, setShowUserInfo] = useState(false);

  const addboardHandler = (name) => {
    const newBoard = {
      id: Date.now() + Math.random() * 2,
      //id: uuid(),

      title: name,
      cards: [],
    };
  
    const tempBoards = [...boards, newBoard];
    setBoards(tempBoards);
  
    // Print the id of the newly added board
   // console.log(newBoard.id);
    /*const tempBoards = [...boards];
    tempBoards.push({
      id: Date.now() + Math.random() * 2,
      title: name,
      cards: [],
    });
    setBoards(tempBoards);
    console.log(tempBoards.title);*/
    fetch('http://localhost:8080/board', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: newBoard.id,title:name })
    })
    .then(response => response.json())
    .then(data => {
        setBoards([...boards, data]);
    })
    .catch(error => console.error('Error adding board:', error));
  };

  const removeBoard = (id) => {
    const index = boards.findIndex((item) => item.id === id);
    if (index < 0) return;

    const tempBoards = [...boards];
    tempBoards.splice(index, 1);
    setBoards(tempBoards);

    //FETCH API for delete board
    fetch(`http://localhost:8080/board/deleteBoard`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        bid:id
      })
    });    
 //FETCH API END

  };

  const addCardHandler = (id, title) => {
    //console.log(id+"+"+title);
    const index = boards.findIndex((item) => item.id === id);
   // console.log(index);
    if (index < 0) return;

    const tempBoards = [...boards];
    tempBoards[index].cards = tempBoards[index].cards || [];
    const newCardId = Math.floor(Date.now() + Math.random() * 2);
    /*Local Memory*/
    tempBoards[index].cards.push({
      id: newCardId,
      title,
      labels: [],
      date: "",
      tasks: [],
    });
    setBoards(tempBoards);

//FETCH API

//console.log(newCardId+"+"+id);

      fetch(`http://localhost:8080/board/${id}/addCard`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          id:newCardId,
          title:title,
          tasks:[],
          labels:[],
          date:Date.now()
        })
      });
  
      
   //FETCH API END


  };

  const removeCard = (bid, cid) => {
    const index = boards.findIndex((item) => item.id === bid);
    if (index < 0) return;

    const tempBoards = [...boards];
    const cards = tempBoards[index].cards;

    const cardIndex = cards.findIndex((item) => item.id === cid);
    if (cardIndex < 0) return;

    cards.splice(cardIndex, 1);
    setBoards(tempBoards);

    //FETCH API for delete card from board
      fetch(`http://localhost:8080/board/deleteCard`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          bid:bid,
          cid:cid
        })
      });    
   //FETCH API END

  };

  const dragEnded = (bid, cid) => {
    let s_boardIndex, s_cardIndex, t_boardIndex, t_cardIndex;
    s_boardIndex = boards.findIndex((item) => item.id === bid);
    //if (s_boardIndex < 0) return;

    s_cardIndex = boards[s_boardIndex]?.cards?.findIndex(
      (item) => item.id === cid
    );
    //if (s_cardIndex < 0) return;

    t_boardIndex = boards.findIndex((item) => item.id === targetCard.bid);
    //if (t_boardIndex < 0) return;

    t_cardIndex = boards[t_boardIndex]?.cards?.findIndex(
      (item) => item.id === targetCard.cid
    );
    //if (t_cardIndex < 0) return;
    // console.log(s_boardIndex+"s_bid");
    // console.log(s_cardIndex+"s_cid");
    // console.log(t_boardIndex+"t_bid");
    // console.log(t_cardIndex+"t_cid");
    //if(t_cardIndex===0) console.log("I am gere");
    const tempBoards = [...boards];
    const sourceCard = tempBoards[s_boardIndex].cards[s_cardIndex];
    console.log(targetCard.cid);
    if (targetCard.cid === null) {
      // Handle board-level drop
      tempBoards[t_boardIndex].cards.push(sourceCard);
    } 
    tempBoards[s_boardIndex].cards.splice(s_cardIndex, 1);
    tempBoards[t_boardIndex].cards.splice(t_cardIndex, 0, sourceCard);
    setBoards(tempBoards);

    setTargetCard({
      bid: "",
      cid: "",
    });
  };

  const dragEntered = (bid, cid) => {
   // console.log(cid+"cid");
   // console.log(bid+"bid");

    if (targetCard.cid === cid) return;
    setTargetCard({
      bid,
      cid,
    });
  };

  const updateCard = (bid, cid, card) => {
    const index = boards.findIndex((item) => item.id === bid);
    if (index < 0) return;

    const tempBoards = [...boards];
    const cards = tempBoards[index].cards;

    const cardIndex = cards.findIndex((item) => item.id === cid);
    if (cardIndex < 0) return;

    tempBoards[index].cards[cardIndex] = card;

    setBoards(tempBoards);
  };

  const handleLogout = () => {
    // Clear user data and redirect to login page
    //setUser(null);
    history.push('/login');
    // You may want to clear any authentication tokens or other user-related data here
  };


  useEffect(() => {
    localStorage.setItem("prac-kanban", JSON.stringify(boards));
  }, [boards]);

  return (
    
    <div className="app">
      

      <div className="app_nav">
        <h1>KANBAN BOARD</h1>
        <div className="navbar_right">
          <button onClick={() => setShowUserInfo(!showUserInfo)}>
            {/* Add your logo or icon here */}
            <div>
      {/* Use the FontAwesomeIcon component */}
      <FontAwesomeIcon icon={faUser} />
    </div>
            </button>
          {showUserInfo && responseData && (
            <div className="user-info">
              <span>Welcome, {username}!</span>
              {/* Add any other user-related information you want to display */}
              <br/>
              <button onClick={handleLogout}>Logout</button>
            </div>
          )}
        </div>
      </div>
      <div className="app_boards_container">
        <div className="app_boards">
          {boards.map((item) => (
            <Board
              key={item.id}
              board={item}
              addCard={addCardHandler}
              removeBoard={() => removeBoard(item.id)}
              removeCard={removeCard}
              dragEnded={dragEnded}
              dragEntered={dragEntered}
              updateCard={updateCard}
            />
          ))}
          <div className="app_boards_last">
            <Editable
              displayClass="app_boards_add-board"
              editClass="app_boards_add-board_edit"
              placeholder="Enter Board Name"
              text="Add Board"
              buttonText="Add Board"
              onSubmit={addboardHandler}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
