import React, { Component } from 'react';
import Navbar from './components/Navbar';
import './App.css';
import Profile from './components/Profile';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Navbar />
        <Profile />
      </div>
    );
  }
}

export default App;
