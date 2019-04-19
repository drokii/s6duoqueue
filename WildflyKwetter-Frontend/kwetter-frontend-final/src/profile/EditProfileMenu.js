import React from 'react';
import { Button, ModalHeader, ModalBody, ModalFooter, Form, FormGroup, Label, Input } from 'reactstrap';
import axios from 'axios'
import { withRouter } from 'react-router-dom';

class EditProfileMenu extends React.Component {

    state = {
        username: '',
        bio: '',
        location: '',
        website: '',
    }

    handleChange = (e) => {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }
    componentDidMount() {
        this.setState({
            username: this.props.username,
            bio: this.props.bio,
            location: this.props.userLocation,
            website: this.props.website
        })
    }

    submitEdit = () => {
        axios.post('/user/edituser', {
            username: localStorage.getItem('username'),
            desiredUsername: this.state.username,
            bio: this.state.bio,
            location: this.state.location,
            website: this.state.website,
        }, {
                headers: { Authorization: localStorage.getItem('token') }
            }
        )
            .then(response => {
                console.log(this.state)
                if (response.status === 200) {
                    if (response.data === "Username has been changed!") {
                        this.props.notify(response.data)
                        localStorage.setItem('username', this.state.username)
                    }
                    else {
                        this.props.notify(response.data)
                    }
                    this.props.updateProfile(this.state.username, this.state.bio,this.state.location, this.state.website)
                }
                else {
                    console.log(response)
                    // todo:bad login msg
                }
            })
            .catch(function (error) {
                console.log(error);
                // todo:bad call msg

            });

    }

    render() {
        
        return (
            <div>
                <ModalHeader toggle={this.props.toggleModal}>Edit Profile</ModalHeader>
                <ModalBody>
                    <FormGroup>
                        <Label for="username">Username</Label>
                        <Input name="username" id="username" placeholder={this.props.username} onChange={this.handleChange} value={this.state.username} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="bio">Biography</Label>
                        <Input type="textarea" name="bio" id="bio" placeholder={this.props.bio} onChange={this.handleChange} value={this.state.bio} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="location">Location</Label>
                        <Input name="location" id="location" placeholder={this.props.location} onChange={this.handleChange} value={this.state.location} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="website">Website</Label>
                        <Input name="website" id="website" placeholder={this.props.website} onChange={this.handleChange} value={this.state.website} />
                    </FormGroup>

                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={(event) => {
                        this.props.toggleModal(); this.submitEdit();
                    }}> Confirm </Button>{' '}

                </ModalFooter>
            </div>

        )

    }

}

export default withRouter(EditProfileMenu)