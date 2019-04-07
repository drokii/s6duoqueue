import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import { Input, InputAdornment, IconButton, InputLabel, FormControl } from '@material-ui/core';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import {withRouter} from 'react-router-dom';

class LoginDialog extends React.Component {
  state = {
    open: false,
    username: '',
    password: ''
  };

  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
  }

  handleClickOpen = () => {
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
  };

  handleLogIn = () => {
    this.props.history.push('/')
    this.handleClose();
  }

  handleChange = prop => event => {
    this.setState({ [prop]: event.target.value });
  };

  handleClickShowPassword = () => {
    this.setState(state => ({ showPassword: !state.showPassword }));
  };

  render() {
    return (
      <div>
        <Button variant="outlined" color="inherit" onClick={this.handleClickOpen}>
          Log in
        </Button>
        <Dialog
          open={this.state.open}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Log in or Sign up with your Kwetter account.</DialogTitle>
          <DialogContent>
            <FormControl>
              <InputLabel htmlFor="username">User Name</InputLabel>
              <Input
                autoFocus
                value={this.state.password}
                margin="dense"
                id="username"
              />
            </FormControl>
            <p></p>
            <FormControl>
              <InputLabel htmlFor="password"> Password</InputLabel>
              <Input
                id="password"
                type={this.state.showPassword ? 'text' : 'password'}
                value={this.state.password}
                onChange={this.handleChange('password')}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="Toggle password visibility"
                      onClick={this.handleClickShowPassword}
                    >
                      {this.state.showPassword ? <Visibility /> : <VisibilityOff />}
                    </IconButton>
                  </InputAdornment>
                }
              />
            </FormControl>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleLogIn} color="primary">
              Login
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}
export default withRouter(LoginDialog);