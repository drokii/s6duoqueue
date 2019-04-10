import React from 'react';
import { Redirect } from 'react-router-dom'
import TweetFeed from './TweetFeed';
import axios from 'axios';
import { Spinner, Container } from 'reactstrap';
import InputTweet from './InputTweet';
import jwt_decode from 'jwt-decode';

class HomePage extends React.Component {

    state = {
        tweets: []
    }

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
        this.addTweet = this.addTweet.bind(this)
    }

    componentDidMount() {
        var activeUser = this.props.getActiveUser()
        this.setState({ activeUser: activeUser })
    }

    addTweet(tweet){
        var newArray = this.state.tweets.slice();    
        newArray.unshift(tweet);   
        this.setState({tweets:newArray})
    }

    retrieveTweets = () => {
        try{
            var url = '/tweet/get/' // replace by tweets by followers soon
            var activeUser =  jwt_decode(localStorage.getItem('token')).sub
            console.log(activeUser)
            axios.get(url.concat(activeUser), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {
                    console.log(response)
                    this.setState({ tweets: response.data })
                    console.log(this.state.tweets)
                })
                .catch(function (error) {
                    console.log(error);
                });
        }catch(e){
            console.log(e)
        }
        
    }

    render() {
        var isAuthenticated = localStorage.getItem('token');
        console.log(isAuthenticated)
        if (isAuthenticated === null) {
            console.log('no auth!')
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
                        <InputTweet activeUser={this.state.activeUser} addTweet={this.addTweet} />
                        <TweetFeed tweets={this.state.tweets} />
                    </Container>
                </div>
            );
        }


    }
}
export default HomePage;