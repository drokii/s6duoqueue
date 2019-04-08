import {
    Card, CardText, CardBody,
    CardTitle, CardSubtitle
} from 'reactstrap';
import React from 'react';


const Tweet = (props) => {

    return (
        <Card>
            <CardBody>
                <CardTitle><b>{props.author}</b></CardTitle>
                <CardSubtitle>{props.message}</CardSubtitle>
                <CardText>
                <small className="text-muted">{props.date}</small></CardText>
            </CardBody>
        </Card>

    );
}

export default Tweet;