import {
    Card, CardText, CardBody,
    CardTitle, CardSubtitle, Row, Col
} from 'reactstrap';
import React from 'react';
import { Link } from 'react-router-dom';

const Tweet = (props) => {

    return (
        <Card>
            <CardBody>
                <Row>
                    <Col xs="auto" >
                        <img style={{ borderRadius: 40, width: 60, height: 60, marginTop:5}} src="https://i.imgur.com/3Gw4MkV.png" alt="Avatar" />
                    </Col>
                    <Col style={{ padding:0}}>
                        <CardTitle>
                            <Link style={{ color: 'black', textDecoration: 'none' }} to={{ pathname: '/profile', state: props.authorId }} >
                            <h5>{props.author}</h5>
                            </Link>
                        </CardTitle>
                        <CardSubtitle style={{marginLeft:5,marginTop: 5 }} ><h6>{props.message}</h6></CardSubtitle>
                        <CardText style={{ marginTop: 10}}>
                            <small className="text-muted">{props.date}</small>
                        </CardText>
                    </Col>
                </Row>


            </CardBody>
        </Card>

    );
}

export default Tweet;