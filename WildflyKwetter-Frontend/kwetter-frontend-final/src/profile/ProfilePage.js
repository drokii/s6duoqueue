import React from 'react';
import { Spinner, Container, Row, Col, CardImg, CardBody, CardTitle, CardSubtitle, CardText, Button, Card } from 'reactstrap';
import { Redirect } from 'react-router-dom';
import TweetFeed from '../feed/TweetFeed';
import jwt_decode from 'jwt-decode';
import axios from 'axios';
import InputTweet from '../feed/InputTweet';

class ProfilePage extends React.Component {

    state = {
        userId: '',
        username: '',
        bio: '',
        website: '',
        location: '',
        tweets: []
    }

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
        this.retrieveUser = this.retrieveUser.bind(this)
    }

    componentDidMount() {
        this.setState({ userId: this.props.location.state })
    }


    retrieveTweets = () => {
        try {
            var url = '/tweet/get/' // replace by tweets by followers soon
            var activeUser = this.state.userId
            axios.get(url.concat(activeUser), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {
                    console.log(response)
                    this.setState({ tweets: response.data })
                    console.log(this.state.tweets)
                })
                .catch(function (error) {
                    console.log(error);
                });
        } catch (e) {
            console.log(e)
        }

    }

    retrieveUser= () => {
        try {
            var url = '/user/'
            axios.get(url.concat(this.state.userId), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {
                    console.log(response)
                    this.setState({ 
                        username: response.data.username,
                        website : response.data.website,
                        location : response.data.location,
                        bio : response.data.bio
                    })
                    console.log(this.state.tweets)
                })
                .catch(function (error) {
                    console.log(error);
                });
        } catch (e) {
            console.log(e)
        }

    }

    render() {

        if (this.props.location.state === null) {
            return <Redirect to='/' />
        };

        if (this.state.tweets.length === 0) {
            this.retrieveTweets()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        if (this.state.username === '') {
            this.retrieveUser()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        return (
            <div>
                <Container style={{ marginTop: 20 }}>
                    <Row>
                        <Col xs="4">
                            <Row>
                                <Col>
                                    <Card>
                                        <div style={{ margin: 'auto', padding: 20 }}>
                                            <img style={{ borderRadius: 80, width: 160, height: 160 }} src="https://i.imgur.com/3Gw4MkV.png" alt="Avatar" />
                                        </div>
                                        <CardBody style={{textAlign:'center'}}>
                                            <CardTitle><b>{this.state.username}</b></CardTitle>
                                            <small className="text-muted">Bio</small>
                                            <CardSubtitle>{this.state.bio}</CardSubtitle>
                                            <small className="text-muted">Location</small>
                                            <CardSubtitle>{this.state.location}</CardSubtitle>
                                            <small className="text-muted">Website</small>
                                            <CardText>{this.state.website}</CardText>

                                            <Button style={{ marginRight: 10 }}>Follow</Button>
                                            <Button >Edit</Button>

                                        </CardBody>
                                    </Card>
                                </Col>
                            </Row>

                        </Col>

                        <Col>
                            <Card>
                                <InputTweet activeUser={this.state.activeUser} addTweet={this.retrieveTweets} />

                                <TweetFeed tweets={this.state.tweets} />
                            </Card>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="6" sm="4" style={{ marginTop: 20 }}>
                            <Card>
                                <CardBody>

                                </CardBody>
                            </Card>
                        </Col>

                    </Row>
                </Container>
            </div>

        )
    }

}
export default ProfilePage