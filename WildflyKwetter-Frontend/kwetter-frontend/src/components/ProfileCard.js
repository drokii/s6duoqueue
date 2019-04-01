import React from 'react'
import Card from '@material-ui/core/Card'
import CardContent from '@material-ui/core/CardContent'
import Avatar from '@material-ui/core/Avatar';
import { Grid } from '@material-ui/core';



const ProfileCard = (props) => {
    console.log('inside profile')
    console.log(props)

    return (
        
        <div style={{ padding: 12, minWidth: '40vw'}} >
            <Card display="flex"  >
                <div id='personalInfo'>
                    <div>
                        <CardContent display="flex" style={{ display: "inline-block" }} >
                            <Avatar style={{ width: 160, height: 160 }} src="https://i.imgur.com/3Gw4MkV.png" />
                        </CardContent>
                    </div>
                    <div style={{ margin: 10 }} >
                        <h3 style={{ margin: 0 }}>{props.user.username}</h3>
                        <i>Bio 🖊️</i>
                        <p style={{ margin: 0 }}>{props.user.bio}</p>
                        <br></br>
                        <i>Location 📍</i>
                        <p style={{ margin: 0 }}>{props.user.location}</p>
                        <br></br>
                        <i>Website 📰</i>
                        <p style={{ margin: 0 }}>{props.user.website}</p>
                        <br></br>

                    </div>
                </div> 
                </Card>
        </div>
    )
}
export default ProfileCard