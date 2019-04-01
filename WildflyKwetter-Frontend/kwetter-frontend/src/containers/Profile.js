import { Component } from "react";
import axios from 'axios';
import React from 'react'
import { Grid } from "@material-ui/core";
import ProfileCard from "../components/ProfileCard";



class Profile extends Component{
    state = {
        searchString: '',
        user: [],
        
    }

    componentDidMount() {
        axios.get('http://localhost:4000/profile')
            .then(response => {
                console.log(response);
                this.setState({user: response.data.user})
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    render() {
        
        return (
            <div>
                    <div>
                        <Grid container spacing={24} style={{padding: 24}} direction="column"
                            justify="center"
                            alignItems="center"
                            >
                            <Grid item xs={6} sm={6} lg={6} xl={6}>
                            <ProfileCard user={this.state.user}/>     
                            </Grid>

                        </Grid>
                    </div>
            </div>
        )
    }
}
export default Profile;
