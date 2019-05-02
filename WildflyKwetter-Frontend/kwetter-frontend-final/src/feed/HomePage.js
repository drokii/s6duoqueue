import React from 'react';
import { Redirect } from 'react-router-dom'
import TweetFeed from './TweetFeed';
import axios from 'axios';
import { Col, Row, Spinner, Container } from 'reactstrap';
import InputTweet from './InputTweet';
import jwt_decode from 'jwt-decode';
 
class HomePage extends React.Component {

    state = {
        tweets: []
    }

    webSocket;

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
        this.webSocket = props.webSocket
    }

    
    componentDidMount() {
        var activeUser = this.props.getActiveUser()
        this.setState({ activeUser: activeUser })

        this.webSocket.onmessage = evt =>{
            this.retrieveTweets()
        }
    }

    

    retrieveTweets = () => {
        try {
            var url = '/tweet/followers/' // replace by tweets by followers soon
            var activeUser = jwt_decode(localStorage.getItem('token')).sub
            axios.get(url.concat(activeUser), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {
                    console.log(response)
                    response.data.sort(function(a, b) {
                        var dateA = new Date(a.date), dateB = new Date(b.date);
                        return dateA - dateB;
                    });
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


    render() {
        var isAuthenticated = localStorage.getItem('token');
        if (isAuthenticated === null) {
            return <Redirect to='/login' />
        };

        if (this.state.tweets.length === 0) {
            this.retrieveTweets()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        else {
            return (
                <div style={{ marginTop: 20, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}>
                    <Container>
                        <Row>
                            <Col>
                                <h1 style={{ textAlign: 'center', marginBottom: 20 }}>Kwetter.</h1>
                                <InputTweet webSocket={this.webSocket} activeUser={this.state.activeUser} addTweet={this.retrieveTweets} />
                                <TweetFeed tweets={this.state.tweets} />
                                
                            </Col>
                        </Row>



                    </Container>
                    
                </div>
            );
        }


    }
}
export default HomePage;