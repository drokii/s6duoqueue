import { Container, Col, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import React from 'react';
import axios from 'axios';


class LoginPage extends React.Component {

    state = { username: '', password: '', }

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        logIn(e.target.username, e.target.password)
    }

    handleChange(e) {
        this.setState({ value: e.target.value })
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
                                onChange= {this.handleChange}

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
                                onChange= {this.handleChange}
                            />
                        </FormGroup>
                        <Button style={{ margin: 'auto', display: 'block' }}>Submit</Button>
                    </Col>

                </Form>
            </Container>
        );
    }


}

export default LoginPage;

function logIn(name, pass) {
    axios.post('/authentication', {
        username: name.toString,
        password: pass.toString
    })
        .then(function (response) {
            console.log(response);
            return true;
        })
        .catch(function (error) {
            console.log(error);
            return false;
        });
}

