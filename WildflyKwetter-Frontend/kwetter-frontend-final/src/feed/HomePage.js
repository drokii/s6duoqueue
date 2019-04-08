import React from 'react';
import { Redirect } from 'react-router-dom'
import TweetFeed from './TweetFeed';
import axios from 'axios';
import { Spinner, Container } from 'reactstrap';
import InputTweet from './InputTweet';

class HomePage extends React.Component {

    state = {
        activeUser: '',
        tweets: []
    }

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
    }

    componentDidMount() {
        var activeUser = this.props.getActiveUser()
        this.setState({ activeUser: activeUser })
    }

    retrieveTweets = () => {
        var url = '/tweet/get/' // replace by tweets by followers soon
        axios.get(url.concat(this.state.activeUser), { headers: { Authorization: localStorage.getItem('token') } })
            .then(response => {
                console.log(response)
                this.setState({ tweets: response.data })
                console.log(this.state.tweets)
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    render() {
        var isAuthenticated = this.props.checkWhetherAuthenticated()

        if (!isAuthenticated) {
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
                        <InputTweet activeUser={this.state.activeUser} handlePostTweet={this.handlePostTweet} />
                        <TweetFeed tweets={this.state.tweets} />
                    </Container>
                </div>
            );
        }


    }
}
export default HomePage;