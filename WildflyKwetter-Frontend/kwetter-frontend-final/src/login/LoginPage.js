import { Container, Col, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import React from 'react';
import axios from 'axios';


class LoginPage extends React.Component {

    state = { username: '', password: '', }

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.logIn = this.logIn.bind(this);

    }

    handleSubmit(e) {
        e.preventDefault();
        this.logIn(e.target.username, e.target.password);
    }

    handleChange(e) {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }

    logIn() {
        // todo : Shove this elsewhere

        axios.post('/authentication', {
            username: this.state.username,
            password: this.state.password
        })
            .then(function (response) {
                console.log(response)
                axios.defaults.headers.common['Authorization'] = response.data;
            })
            .catch(function (error) {
                console.log(error);
                return null;
            });

        this.props.authenticate();
        this.props.history.push('/')
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
                    </Col>

                </Form>
            </Container>
        );
    }


}

export default withRouter(LoginPage);



