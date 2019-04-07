import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginPage from '../login/LoginPage';
import 'bootstrap/dist/css/bootstrap.min.css';
import FeedPage from '../feed/FeedPage';

class App extends Component {

  state = {
    isAuthenticated: false
  }

  constructor(props) {
    super(props)
    this.authenticate = this.authenticate.bind(this)
  }

  authenticate() {
    console.log('authorized!')
    this.setState({ isAuthenticated: true })
  }

  render() {
    return (
      <div className="App">
        <Switch>
          <Route path="/login" exact render={(props) => <LoginPage {...props} authenticate={this.authenticate} />} />
          <Route path="/" exact component={() => <FeedPage isAuthenticated={this.state.isAuthenticated} />} />
        </Switch>
      </div>
    );
  }
}

export default App;
