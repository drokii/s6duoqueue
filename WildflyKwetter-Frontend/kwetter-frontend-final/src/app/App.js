import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginPage from '../login/LoginPage';
import 'bootstrap/dist/css/bootstrap.min.css';
import HomePage from '../feed/HomePage';
import ProfilePage from '../profile/ProfilePage'

class App extends Component {

  state = {
    isAuthenticated: false,
    activeUser: ''

  }

  webSocket = new WebSocket("ws://localhost:8080/kwetter/kwettersocket")

  constructor(props) {
    super(props)
    this.authenticate = this.authenticate.bind(this)
    this.checkWhetherAuthenticated = this.checkWhetherAuthenticated.bind(this)
    this.getActiveUser = this.getActiveUser.bind(this)
  }

  authenticate(username) {
    this.setState({
      isAuthenticated: true,
      activeUser: username,
    })
  }

  checkWhetherAuthenticated() {
    return this.state.isAuthenticated
  }

  getActiveUser() {
    return this.state.activeUser
  }

  render() {
    return (
      <div className="App">
        <Switch>
          <Route path="/login" exact render={(props) => <LoginPage {...props} authenticate={this.authenticate} />} />
          <Route path="/" exact render={(props) => <HomePage {...props} checkWhetherAuthenticated={this.checkWhetherAuthenticated} getActiveUser={this.getActiveUser} webSocket={this.webSocket}/>} />
          <Route path="/profile" exact render={(props) =><ProfilePage {...props} webSocket={this.webSocket}/> } />
        </Switch>
      </div>
    );
  }
}

export default App;
