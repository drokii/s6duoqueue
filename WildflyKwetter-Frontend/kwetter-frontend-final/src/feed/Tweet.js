import {
    Card, CardText, CardBody,
    CardTitle, CardSubtitle
} from 'reactstrap';
import React from 'react';
import { Link } from 'react-router-dom';

const Tweet = (props) => {

    return (
        <Card>
            <CardBody>
                <CardTitle>
                    <Link to={{
                        pathname: '/profile',
                        state: { username: props.author }
                    }}>{props.author}</Link>
                </CardTitle>
                <CardSubtitle>{props.message}</CardSubtitle>
                <CardText>
                    <small className="text-muted">{props.date}</small></CardText>
            </CardBody>
        </Card>

    );
}

export default Tweet;