import React from 'react';
import { Spinner, Container, Row, Col, CardBody, CardTitle, CardSubtitle, CardText, Button, Card, Modal } from 'reactstrap';
import { Redirect, Link } from 'react-router-dom';
import TweetFeed from '../feed/TweetFeed';
import axios from 'axios';
import InputTweet from '../feed/InputTweet';
import EditProfileMenu from './EditProfileMenu';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { withRouter } from 'react-router-dom';
import FollowerAvatar from './FollowerAvatar';
import jwt_decode from 'jwt-decode';

class ProfilePage extends React.Component {

    state = {
        userId: '',
        username: '',
        bio: '',
        website: '',
        location: '',
        tweets: [],
        modal: false,
        followers: [],
        following: [],
        retrieved: false
    }

    webSocket;

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
        this.retrieveUser = this.retrieveUser.bind(this)
        this.toggleModal = this.toggleModal.bind(this)
        this.notify = this.notify.bind(this)
        this.updateProfile = this.updateProfile.bind(this)

        this.webSocket = props.webSocket

        this.renderFollowButton = this.renderFollowButton.bind(this)
        this.renderEditProfile = this.renderEditProfile.bind(this)
    }

    componentWillReceiveProps(nextProps) {
        if (this.props.location.state !== nextProps.location.state) {
            window.location.reload()
        }
    }

    componentDidMount() {
        this.setState({ userId: this.props.location.state })

        this.webSocket.onmessage = evt => {
            console.log("Tweet Received Via WebSocket")
            this.retrieveTweets()
        }
    }

    updateProfile = (username, bio, location, website) => {
        this.setState({
            username: username,
            bio: bio,
            website: website,
            location: location,
        })
    }

    retrieveTweets = () => {
        try {
            var url = '/tweet/get/'
            var activeUser = this.state.userId
            axios.get(url.concat(activeUser), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {

                    this.setState({
                        tweets: response.data,
                        retrieved: true
                    })
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

    followUser = () => {
        axios.post('/user/follow', {
            username: localStorage.getItem('username'),
            followed: this.state.username
        },
            {
                headers: { Authorization: localStorage.getItem('token') }
            })
            .then(response => {
                toast("You are now following " + this.state.username)
                var newFollowers = this.state.followers;
                var newFollower = {
                    id: jwt_decode(localStorage.getItem('token')).sub,
                    username: localStorage.getItem('username')
                }

                newFollowers.push(newFollower)

                this.setState(prevState => ({

                    followers: newFollowers
                }));

                this.renderFollowButton()
                console.log(response)
            })
            .catch(function (error) {
                console.log(error)
                toast("Something went wrong: " + error)

            });

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
                    bio: response.data.bio,
                    followers: response.data.followers,
                    following: response.data.following
                })
                console.log(this.state)
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    renderFollowButton = () => {
        var filter = this.state.followers.filter(follower => (follower.username === localStorage.getItem('username')))

        if (filter.length < 1 && localStorage.getItem('username') !== this.state.username) {
            return <Button color="primary" onClick={this.followUser} style={{ marginRight: 10 }}>Follow</Button>
        }

    }

    renderEditProfile = () => {
        console.log("decoded token")
        console.log(jwt_decode(localStorage.getItem('token')).sub)
        console.log(this.state.userId)

        if (this.state.userId === jwt_decode(localStorage.getItem('token')).sub) {
            return <Button color="primary" onClick={this.toggleModal}>Edit Profile</Button>
        }
    }

    renderInputTweet = () => {
        if (this.state.userId === jwt_decode(localStorage.getItem('token')).sub) {
            return <InputTweet webSocket={this.webSocket} activeUser={this.state.activeUser} addTweet={this.retrieveTweets} />
        }
    }

    render() {

        var isAuthenticated = localStorage.getItem('token');
        if (isAuthenticated === null) {
            return <Redirect to='/login' />
        };

        if (this.props.location.state === null) {
            return <Redirect to='/' />
        };

        if (this.state.username === '') {
            this.retrieveUser()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        if (!this.state.retrieved) {
            this.retrieveTweets()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }

        const renderFollow = this.renderFollowButton();
        const renderEdit = this.renderEditProfile();
        const renderInputTweet = this.renderInputTweet();

        const followers = this.state.followers.map(follower => {
            return <FollowerAvatar username={follower.username} id={follower.id} navigateToOtherUser={this.navigateToOtherUser} />;
        });

        const following = this.state.following.map(following => {
            return <FollowerAvatar username={following.username} id={following.id} navigateToOtherUser={this.navigateToOtherUser} />;
        });
        console.log(this.state)
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
                                            <CardTitle><h3>{this.state.username}</h3></CardTitle>
                                            <small className="text-muted">Bio</small>
                                            <CardSubtitle>{this.state.bio}</CardSubtitle>
                                            <small className="text-muted">Location</small>
                                            <CardSubtitle>{this.state.location}</CardSubtitle>
                                            <small className="text-muted">Website</small>
                                            <CardText>{this.state.website}</CardText>

                                            {renderFollow}
                                            {renderEdit}

                                            <div style={{ marginTop: 10, textAlign: 'center', marginBottom: 20 }}>
                                                <Link style={{ color: 'black', textDecoration: 'none' }} to={{pathname: '/'}} >
                                                    <b>Back to Home Page</b>
                                                </Link>
                                            </div>
                                            <Modal isOpen={this.state.modal} toggle={this.toggleModal} className={this.props.className}>
                                                <EditProfileMenu updateProfile={this.updateProfile} notify={this.notify} toggleModal={this.toggleModal} username={this.state.username} userLocation={this.state.location} website={this.state.website} bio={this.state.bio} />
                                            </Modal>

                                        </CardBody>
                                    </Card>
                                </Col>
                            </Row>

                        </Col>

                        <Col>
                            <Card>
                                {renderInputTweet}
                                <TweetFeed tweets={this.state.tweets} />
                            </Card>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="6" sm="4" style={{ marginTop: 20 }}>
                            <Row>
                                <Col style={{ width: 160, paddingRight: 0 }}>
                                    <Card style={{ textAlign: 'center' }}><CardTitle style={{ marginTop: 10 }}><b>Follower: {followers.length}</b></CardTitle></Card>

                                    <Card style={{ height: 160, textAlign: 'center', overflow: 'auto' }}>
                                        <CardBody>
                                            <div style={{ height: '20%' }}>
                                                {followers}
                                            </div>
                                        </CardBody>
                                    </Card>
                                </Col>
                                <Col style={{ width: 160, padding: 0 }}>
                                    <Card style={{ textAlign: 'center' }}><CardTitle style={{ marginTop: 10 }}><b>Following: {following.length}</b></CardTitle></Card>
                                    <Card style={{ height: 160, overflow: 'auto', textAlign: 'center' }}>
                                        <CardBody>
                                            <div style={{ height: '20%' }}>
                                                {following}
                                            </div>
                                        </CardBody>
                                    </Card>
                                </Col>

                            </Row>
                        </Col>


                    </Row>
                </Container>
                <ToastContainer />
            </div>

        )
    }

}
export default withRouter(ProfilePage)