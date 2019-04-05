import React, { Component } from 'react'
import Grid from '@material-ui/core/Grid'
import Tweet from '../components/Tweet';
import axios from 'axios';
import InputTweet from '../components/InputTweet';



class ProfileFeed extends Component {

    state = {
        tweets: [],
        searchString: '',
        
    }

    componentDidMount() {
        axios.get('http://localhost:4000/gettweetsbyuser')
            .then(response => {
                console.log(response);
                const tweets = response.data.tweets.slice(0,10);
                const updatedTweets = tweets.map(tweet=> {
                    return {
                        ...tweet,
                    }
                });
                this.setState({ tweets: updatedTweets});

            })
            .catch(function (error) {
                console.log(error);
            })
    }

    render() {
          
        const tweets = this.state.tweets.map(tweet => {
            return <Tweet author={tweet.author} message={tweet.message} date={tweet.date} randomProfilePicture={"https://i.imgur.com/3Gw4MkV.png"}  />;
        });

        return (
            <div style={{padding: 24, maxWidth:'50vw', overflow: 'auto', maxHeight:'80vh'}}>
                {this.state.tweets ? (
                    <div >
                        <Grid container spacing={24} style={{margin: -50, padding: 24}} direction="column"
                            justify="flex-start"
                            alignItems="center"
                            >
                            <Grid item xs={6} sm={6} lg={6} xl={6}>
                            {tweets}
                            </Grid>
                        </Grid>
                    </div>
                ) : "No tweets found"}
            </div>
        )
    }
}


export default ProfileFeed;