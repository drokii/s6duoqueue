import React from 'react';
import { Spinner, Container, Row, Col, CardImg, CardBody, CardTitle, CardSubtitle, CardText, Button, Card, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { Redirect } from 'react-router-dom';
import TweetFeed from '../feed/TweetFeed';
import jwt_decode from 'jwt-decode';
import axios from 'axios';
import InputTweet from '../feed/InputTweet';
import EditProfileMenu from './EditProfileMenu';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { withRouter } from 'react-router-dom';

class ProfilePage extends React.Component {

    state = {
        userId: '',
        username: '',
        bio: '',
        website: '',
        location: '',
        tweets: [],
        modal: false
    }

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
        this.retrieveUser = this.retrieveUser.bind(this)
        this.toggleModal = this.toggleModal.bind(this)
        this.notify = this.notify.bind(this)
    }

    componentDidMount() {
        this.setState({ userId: this.props.location.state })
    }


    retrieveTweets = () => {
        try {
            var url = '/tweet/get/' // replace by tweets by followers soon
            var activeUser = this.state.userId
            axios.get(url.concat(activeUser), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {
                    this.setState({ tweets: response.data })
                })
                .catch(function (error) {
                    console.log(error);
                });
        } catch (e) {
            console.log(e)
        }

    }

    toggleModal() {
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
    }

    notify = (message) => {
        toast(message)
    }

    retrieveUser = () => {

        var url = '/user/'
        axios.get(url.concat(this.state.userId))
            .then(response => {
                console.log(response)
                this.setState({
                    username: response.data.username,
                    website: response.data.website,
                    location: response.data.location,
                    bio: response.data.bio
                })
                console.log(this.state)
            })
            .catch(function (error) {
                console.log(error);
            });


    }

    render() {

        var isAuthenticated = localStorage.getItem('token');
        if (isAuthenticated === null) {
            return <Redirect to='/login' />
        };

        if (this.props.location.state === null) {
            return <Redirect to='/' />
        };

        if (this.state.tweets.length === 0) {
            this.retrieveTweets()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        if (this.state.username === '') {
            this.retrieveUser()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        return (
            <div>
                <Container style={{ marginTop: 20 }}>
                    <Row>
                        <Col xs="4">
                            <Row>
                                <Col>
                                    <Card>
                                        <div style={{ margin: 'auto', padding: 20 }}>
                                            <img style={{ borderRadius: 80, width: 160, height: 160 }} src="https://i.imgur.com/3Gw4MkV.png" alt="Avatar" />
                                        </div>
                                        <CardBody style={{ textAlign: 'center' }}>
                                            <CardTitle><b>{this.state.username}</b></CardTitle>
                                            <small className="text-muted">Bio</small>
                                            <CardSubtitle>{this.state.bio}</CardSubtitle>
                                            <small className="text-muted">Location</small>
                                            <CardSubtitle>{this.state.location}</CardSubtitle>
                                            <small className="text-muted">Website</small>
                                            <CardText>{this.state.website}</CardText>

                                            <Button style={{ marginRight: 10 }}>Follow</Button>
                                            <Button color="primary" onClick={this.toggleModal}>Edit Profile</Button>

                                            <Modal isOpen={this.state.modal} toggle={this.toggleModal} className={this.props.className}>
                                                <EditProfileMenu notify={this.notify} toggleModal={this.toggleModal} username={this.state.username} userLocation={this.state.location} website={this.state.website} bio={this.state.bio} />
                                            </Modal>

                                        </CardBody>
                                    </Card>
                                </Col>
                            </Row>

                        </Col>

                        <Col>
                            <Card>
                                <InputTweet activeUser={this.state.activeUser} addTweet={this.retrieveTweets} />
                                <TweetFeed tweets={this.state.tweets} />
                            </Card>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="6" sm="4" style={{ marginTop: 20 }}>
                            <Card>
                                <CardBody>

                                </CardBody>
                            </Card>
                        </Col>

                    </Row>
                </Container>
                <ToastContainer />
            </div>

        )
    }

}
export default withRouter(ProfilePage)