import React from 'react'
import Card from '@material-ui/core/Card'
import CardContent from '@material-ui/core/CardContent'
import Fab from '@material-ui/core/Fab';
import { TextField } from '@material-ui/core';
import NavigationIcon from '@material-ui/icons/Navigation'

class InputTweet extends React.Component {

    state = {
        message: ''

    };

    handleChange = message => event => {
        this.setState({ [message]: event.target.value });
    };

    render() {
        return (
            <div style={{ padding: 12, minWidth: '35vw' }} >
                <Card display="flex" >
                    <div>
                        <CardContent display="flex" style={{ verticalAlign: "middle" }} >
                            <TextField multiline style={{ verticalAlign: "middle", minWidth: '35vw' }} label="Share your thoughts." value={this.state.message} onChange={this.handleChange('message')} ></TextField>
                        </CardContent>
                    </div>
                    <div style={{ margin: 20 }} >
                        <Fab color="primary" variant="primary" aria-label="Send">
                            <NavigationIcon />
                        </Fab>
                    </div>

                </Card>
            </div>
        )
    }
}

export default InputTweet;