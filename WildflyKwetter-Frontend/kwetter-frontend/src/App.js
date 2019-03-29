import React, { Component } from 'react';
import './App.css';
import NavBar from './containers/Navbar';
import Feed from './containers/Feed';
import InputTweet from './components/InputTweet';

class App extends Component {
  render() {
    return (
      <div className="App">
        <NavBar />
        <Feed />
      </div>
    );
  }
}

export default App;
