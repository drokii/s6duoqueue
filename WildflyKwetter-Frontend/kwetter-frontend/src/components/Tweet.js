import React from 'react'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardContent from '@material-ui/core/CardContent'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import Avatar from '@material-ui/core/Avatar';
import { withStyles } from '@material-ui/core/styles';


const Tweet = (props) => {

    return (
        <div style={{ padding: 12, width: 800 }} >
            <Card display="flex"  >
                <div>
                    <CardContent display="flex" style={{ padding: 6 }} >
                        <Avatar style={{ display: "inline-block", verticalAlign: "middle", marginRight:12 }} src={props.randomProfilePicture} />
                            <h4 style={{ display: "inline-block"}}>{props.author}</h4>
                    </CardContent>
                </div>
                <div style={{ margin: 10 }} >
                    <p style={{ margin: 0 }}>{props.message}</p>
                    <p>
                    </p>
                    <i>Posted around: {props.date}</i>
                </div>

            </Card>
        </div>
    )
}
export default Tweet