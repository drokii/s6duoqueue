import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginPage from '../login/LoginPage';
import 'bootstrap/dist/css/bootstrap.min.css';
import HomePage from '../feed/HomePage';

class App extends Component {

  state = {
    isAuthenticated: false,
    activeUser: ''

  }

  constructor(props) {
    super(props)
    this.authenticate = this.authenticate.bind(this)
    this.checkWhetherAuthenticated = this.checkWhetherAuthenticated.bind(this)
    this.getActiveUser = this.getActiveUser.bind(this)
  }

  authenticate(username) {
    console.log('authorized!')
    console.log(username)
    this.setState({
      isAuthenticated: true,
      activeUser: username,
    })
  }

  checkWhetherAuthenticated() {
    return this.state.isAuthenticated
  }

  getActiveUser(){
    return this.state.activeUser
  }

  render() {
    return (
      <div className="App">
        <Switch>
          <Route path="/login" exact render={(props) => <LoginPage {...props} authenticate={this.authenticate} />} />
          <Route path="/" exact render={(props) => <HomePage {...props} checkWhetherAuthenticated={this.checkWhetherAuthenticated} getActiveUser={this.getActiveUser} />} />
        </Switch>
      </div>
    );
  }
}

export default App;
