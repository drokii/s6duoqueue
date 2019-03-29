import React from 'react'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardContent from '@material-ui/core/CardContent'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import Avatar from '@material-ui/core/Avatar';
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
            <div style={{ padding: 12, width: 800 }} >
                <Card display="flex" >
                    <div>
                        <CardContent display="flex" style={{ verticalAlign: "middle" }} >
                            <TextField multiline style={{ verticalAlign: "middle", width: 600, height: 50 }} label="Share your thoughts with us." value={this.state.message} onChange={this.handleChange('message')} ></TextField>
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