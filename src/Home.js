

import React from 'react';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import App from './App';
import Login from './Login';
import Signup from './Signup';

const Home = () => {
  return (
    <Router>
        <Switch>
        <Route path="/" exact component={Login} />
          <Route path="/signup" component={Signup} />
          <Route path="/login" component={Login} />
          <Route path="/app" component={App}/>
        </Switch>
    </Router>
  );
};

export default Home;
