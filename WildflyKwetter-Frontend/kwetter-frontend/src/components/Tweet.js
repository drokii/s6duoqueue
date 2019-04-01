import React from 'react'
import Card from '@material-ui/core/Card'
import CardContent from '@material-ui/core/CardContent'
import Avatar from '@material-ui/core/Avatar';


const Tweet = (props) => {

    return (
        <div style={{ padding: 12 , minWidth: '30vw'}} >
            <Card display="flex"  >
                <div>
                    <CardContent display="flex" >
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