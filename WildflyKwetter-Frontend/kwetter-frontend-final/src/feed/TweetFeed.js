import React from 'react';
import Tweet from './Tweet';

 


class TweetFeed extends React.Component {

    render() {

        const tweets = this.props.tweets.map(tweet => {
            return <Tweet authorId={tweet.authorId} author={tweet.author} message={tweet.message} date={tweet.date} />;
        });

        return (
           <div>
            {tweets}
           </div> 
            
        )
    }
}
export default TweetFeed;