import React from 'react';
import {
    Card, Form, FormGroup, Label, Input, CardBody, Col, Button
} from 'reactstrap';
import axios from 'axios';
import { RefreshIndicator } from 'material-ui';



class InputTweet extends React.Component {

    constructor(props) {
        super(props)
        this.handlePostTweet = this.handlePostTweet.bind(this)
    }

    handlePostTweet = () => {

        var tweet = {
            author: this.props.activeUser,
            message: 'FUCK THIS',
            date: 'yeah'
        };

        axios.post('/tweet/post', {
            message: 'FUCK THIS',
            username: this.props.activeUser
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
            });
    };

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <Col>
                            <FormGroup>
                                <Label>Put something out there. Come on.</Label>
                                <Input
                                    name="tweet"
                                    id="tweet"
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