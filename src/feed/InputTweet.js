import React from 'react';
import {
    Card, FormGroup, Label, Input, CardBody, Col, Button
} from 'reactstrap';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import {Redirect} from 'react-router-dom'


class InputTweet extends React.Component {

    state = {
        tweet : '' 
    }

    webSocket;

    constructor(props) {
        super(props)
        this.handlePostTweet = this.handlePostTweet.bind(this)
        this.handleChange= this.handleChange.bind(this)
        this.webSocket = props.webSocket;
    }

    handleChange(e) {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }

    handlePostTweet = () => {

        var tweet = {
            author: localStorage.getItem('username'),
            message: this.state.tweet,
        };

        axios.post('/tweet/post', {
            id: jwt_decode(localStorage.getItem('token')).sub,
            message: this.state.tweet
        },
            {
                headers: { Authorization: localStorage.getItem('token') }
            })
            .then(response => {
                this.props.addTweet(tweet)
                console.log(response)
            })
            .catch(function (error) {
                console.log(error)
                return <Redirect to='/login' />
            });

            this.webSocket.send("I posted a tweet.")
    };

    render() {
        return (
            <div style={{marginBottom: 20}}>
                <Card>
                    <CardBody>
                        <Col>
                            <FormGroup>
                                <Label>Put something out there. Come on.</Label>
                                <Input
                                    name="tweet"
                                    id="tweet"
                                    value = {this.state.tweet}
                                    onChange= {this.handleChange}
                                />
                            </FormGroup>
                        </Col>
                        <Button style={{ margin: 'auto', display: 'block' }} onClick={this.handlePostTweet}>Submit</Button>
                    </CardBody>
                </Card>
            </div>
        );
    }



};

export default InputTweet;