import React, { Component } from 'react';
import {Route} from 'react-router-dom';
import LoginPage from '../login/LoginPage';
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Route path="/login" exact component= { LoginPage } />
      </div>
    );
  }
}

export default App;
