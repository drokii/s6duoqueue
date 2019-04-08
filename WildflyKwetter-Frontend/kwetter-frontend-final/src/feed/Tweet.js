import {
    Card, CardText, CardBody,
    CardTitle, CardSubtitle
} from 'reactstrap';
import React from 'react';


const Tweet = (props) => {

    return (
        <Card>
            <CardBody>
                <CardTitle>{props.author}</CardTitle>
                <CardSubtitle>{props.message}</CardSubtitle>
                <CardText>{props.date}</CardText>
            </CardBody>
        </Card>

    );
}

export default Tweet;