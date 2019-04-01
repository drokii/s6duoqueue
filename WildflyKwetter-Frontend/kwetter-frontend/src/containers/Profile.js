import { Component } from "react";
import axios from 'axios';
import React from 'react'
import { Grid, Card, ListItem, List } from "@material-ui/core";
import ProfileCard from "../components/ProfileCard";
import Feed from "./Feed";
import ProfileFeed from "../components/ProfileFeed";



class Profile extends Component {
    state = {
        searchString: '',
        user: [],
        followers: [],
        following: []

    }

    componentDidMount() {
        axios.get('http://localhost:4000/profile')
            .then(response => {
                console.log(response);
                this.setState({ user: response.data.user, followers: response.data.user.followers, following: response.data.user.following })
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    render() {

        const followers = this.state.followers.map(follower => {
            return <ListItem>{follower.name}</ListItem>;
        });

        const following = this.state.following.map(follower => {
            return <ListItem>{follower.name}</ListItem>;
        });

        return (
            <div>
                <div>
                    <Grid container spacing={24} style={{ padding: 24 }} direction="row"
                        justify="flex-start"
                        alignItems="flex-start"
                    >
                        <Grid item xs={6} sm={6} lg={6} xl={6} >
                            <ProfileCard user={this.state.user} />
                            <Card display="flex" style={{ display: 'inline-block', margin: 12, padding: 12, width: '21vw', maxHeight: 200, overflow: 'auto' }}>
                                <h3>Followers:</h3>
                                <List style={{ display: 'inline-block' }}>
                                    {followers}
                                </List>
                            </Card>
                            <Card display="flex" style={{ display: 'inline-block', margin: 12, padding: 12, width: '22vw', maxHeight: 200, overflow: 'auto' }}>
                                <h3>Following:</h3>
                                <List style={{ display: 'inline-block' }}>
                                    {following}
                                </List>

                            </Card>
                        </Grid>
                        <Grid style={{minWidth:'45vw'}}>
                        <ProfileFeed />
                        </Grid>

                    </Grid>
                    


                </div>
            </div>
        )
    }
}
export default Profile;
