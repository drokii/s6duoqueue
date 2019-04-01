import React, { Component } from 'react';
import './App.css';
import NavBar from './containers/Navbar';
import Feed from './containers/Feed';
import {Route} from 'react-router-dom';
import Profile from './containers/Profile';


class App extends Component {
  render() {
    return (
      <div className="App">
        <NavBar />
        <Route path="/profile" component= { Profile } />
        <Route  path="/" exact component={ Feed } />
      </div>
    );
  }
}

export default App;
