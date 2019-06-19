import React from 'react';
import { Redirect, Link } from 'react-router-dom'
import TweetFeed from './TweetFeed';
import axios from 'axios';
import { Col, Row, Spinner, Container, FormGroup, ModalHeader, Modal, ModalBody, Label, Input, Button } from 'reactstrap';
import InputTweet from './InputTweet';
import jwt_decode from 'jwt-decode';


class HomePage extends React.Component {

    state = {
        tweets: [],
        searchTweets: [],
        retrieved: false,
        toggleModal: false,
        search: ''
    }

    webSocket;

    constructor(props) {
        super(props)
        this.retrieveTweets = this.retrieveTweets.bind(this)
        this.webSocket = props.webSocket
        this.retrieveSearch = this.retrieveSearch.bind(this)
        this.toggleModal = this.toggleModal.bind(this)
        this.checkSearchEmpty = this.checkSearchEmpty.bind(this)

    }


    componentDidMount() {
        var activeUser = this.props.getActiveUser()
        this.setState({ activeUser: activeUser })

        this.webSocket.onmessage = evt => {
            this.retrieveTweets()
            console.log("Tweet Received Via WebSocket")

        }
    }

    retrieveSearch = () => {
        
        try {
            var url = '/tweet/search/'
            axios.get(url.concat(this.state.search), { headers: { Authorization: localStorage.getItem('token') } })
                .then(response => {
                    console.log(response)
                    this.setState({
                        searchTweets: response.data,
                    })
                    this.toggleModal()
                })
                .catch(function (error) {
                    console.log(error);
                });
        } catch (e) {
            console.log(e)
        }
    }

    handleChange = (e) => {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }

    retrieveTweets = () => {
        try {
            var url = '/tweet/followers/' 
            var activeUser = jwt_decode(localStorage.getItem('token')).sub
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

    checkSearchEmpty = () => {
        console.log("Searchtweets length")

        console.log(this.state.searchTweets.length)
        if (this.state.searchTweets.length > 0) {
            return <TweetFeed tweets={this.state.searchTweets}/>

        } else {
            return <h1>No Tweets Found</h1>
        }

    }

    toggleModal() {
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
        
    }


    render() {

        const search = this.checkSearchEmpty()
        var isAuthenticated = localStorage.getItem('token');
        if (isAuthenticated === null) {
            return <Redirect to='/login' />
        };

        if (!this.state.retrieved) {
            this.retrieveTweets()
            return <div style={{ marginTop: 50, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}> <Spinner style={{ margin: 'auto', display: 'block', width: '3rem', height: '3rem' }} type="grow" /> </div>
        }


        else {
            return (

                <div style={{ marginTop: 20, width: '50vw', marginLeft: 'auto', marginRight: 'auto' }}>

                    <Container>
                        <Row>
                            <Col>
                                <h1 style={{ textAlign: 'center', marginBottom: 20 }}>Kwetter.</h1>
                                <div style={{ margin: 'auto', textAlign: 'center', marginBottom: 20 }}>
                                    <Link style={{ color: 'black', textDecoration: 'none' }} to={{ pathname: '/profile', state: jwt_decode(localStorage.getItem('token')).sub }} >
                                        <b>Welcome, {localStorage.getItem("username")}.</b>
                                    </Link>

                                </div>
                                <div style={{ marginBottom: 20 }}>
                                    <FormGroup>
                                        <Label for="search">Search</Label>
                                        <Input
                                            type="search"
                                            name="search"
                                            id="search"
                                            placeholder="search tweets"
                                            onChange={this.handleChange}
                                            value={this.state.search}
                                        />
                                    </FormGroup>

                                </div>
                                <Button color="primary" onClick={this.retrieveSearch} style={{ margin: 'auto', display: 'block', marginBottom: 20 }}>Search</Button>
                                <InputTweet webSocket={this.webSocket} activeUser={this.state.activeUser} addTweet={this.retrieveTweets} />
                                <TweetFeed tweets={this.state.tweets} />
                                <Modal isOpen={this.state.modal} toggle={this.toggleModal} className={this.props.className}>
                                    <ModalHeader toggle={this.props.toggleModal}>Search Tweet</ModalHeader>
                                    <ModalBody>
                                        {search}
                                    </ModalBody>
                                </Modal>
                            </Col>
                        </Row>



                    </Container>

                </div>
            );
        }


    }
}
export default HomePage;