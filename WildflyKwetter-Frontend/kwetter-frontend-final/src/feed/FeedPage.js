import React from 'react';
import { Redirect } from 'react-router-dom'

class FeedPage extends React.Component {

    constructor(props) {
        super(props)
        this.state = this.props;
      }

    render() {
        if(!this.props.isAuthenticated){
            console.log('no auth!')
            return <Redirect to='/login' />
        };

        return(
            <p>feed</p>
        );      
    }
}
export default FeedPage;