import React from 'react';
import Tweet from './Tweet';


class TweetFeed extends React.Component {

    state = {
        tweets : []
    }

    render() {
        return (
            
        <Tweet author={'dab'} message={'dab'} date={'dab'} />
        );
    }
}
export default TweetFeed;