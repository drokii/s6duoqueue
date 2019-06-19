import {
    Card, CardBody,
    CardTitle,  Row, Col
} from 'reactstrap';
import React from 'react';
import { Link } from 'react-router-dom';

const FollowerAvatar = (props) => {

    return (
        <Card style={{ width: 130, height: 120, marginBottom: 10, }}>
            <CardBody>

                       <img style={{ borderRadius: 40, width: 60, height: 60 }} src="https://i.imgur.com/3Gw4MkV.png" alt="Avatar" />
                        <CardTitle>
                            <Link style={{ color: 'black', textDecoration: 'none' }}  to={{ pathname: '/profile', state: props.id }} >
                             <b>{props.username}</b>
                            </Link>
                        </CardTitle>
            </CardBody>
        </Card >

    );
}

export default FollowerAvatar;