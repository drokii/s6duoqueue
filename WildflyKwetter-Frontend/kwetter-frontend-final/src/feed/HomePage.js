import React from 'react';
import { Redirect } from 'react-router-dom'
import TweetFeed from './TweetFeed';
import axios from 'axios';
import { Spinner } from 'reactstrap';

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
        var url = '/tweet/get/'
        axios.get(url.concat(this.state.activeUser), { headers: { Authorization: localStorage.getItem('token') } })
            .then(response => {
                this.setState({ tweets: response.data })
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
            return <div style={{ margin: 'auto', width: '30vw' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }
        else {
            return (
                <TweetFeed />
            );
        }


    }
}
export default HomePage;