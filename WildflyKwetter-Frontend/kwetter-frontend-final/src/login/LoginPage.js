import { Container, Col, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import React from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';


class LoginPage extends React.Component {

    state = { username: '', password: '', isAuthed: false }

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.logIn = this.logIn.bind(this);
        this.notify = this.notify.bind(this)

    }

    handleSubmit(e) {
        e.preventDefault();
        this.logIn()
    }

    handleChange(e) {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }



    logIn = () => {
        // todo : Shove this elsewhere
        axios.post('http://127.0.0.1:8080/Kwetter-Auth-Microservice/rest/authentication', {
            username: this.state.username,
            password: this.state.password
        })
            .then(response => {
                if (response.status === 200) {
                    localStorage.setItem('token', 'Bearer '.concat(response.data) )
                    localStorage.setItem('username', this.state.username)
                    this.props.authenticate(this.state.username)
                    this.props.history.push('/')
                }
                else {
                    console.log(response)
                    this.notify(response.data)
                }
            })
            .catch(error => {
                console.log(error);
                this.notify("Your credentials aren't correct.")
            });

    }

    notify = (message) => {
        toast(message)
    }


    render() {
        return (
            <Container className="App" style={{ marginTop: 20, width: '35vw' }}>
                <h2 style={{ textAlign: 'center' }}>Welcome to Kwetter.</h2>
                <Form className="form" onSubmit={this.handleSubmit}>
                    <Col>
                        <FormGroup>
                            <Label>Email</Label>
                            <Input
                                name="username"
                                id="username"
                                value={this.state.username}
                                onChange={this.handleChange}

                            />
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup>
                            <Label for="examplePassword">Password</Label>
                            <Input
                                type="password"
                                name="password"
                                id="password"
                                value={this.state.password}
                                onChange={this.handleChange}
                            />
                        </FormGroup>
                        <Button style={{ margin: 'auto', display: 'block' }} >Submit</Button>
                        <ToastContainer/>
                    </Col>
                </Form>
            </Container>
        );
    }


}

export default withRouter(LoginPage);



