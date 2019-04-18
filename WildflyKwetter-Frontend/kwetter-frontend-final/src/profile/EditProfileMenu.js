import React from 'react';
import { Button, ModalHeader, ModalBody, ModalFooter,Form, FormGroup, Label, Input } from 'reactstrap';

const EditProfileMenu = (props) => {

    return (
        <div>
            <ModalHeader toggle={props.toggleModal}>Edit Profile</ModalHeader>
            <ModalBody>
                <FormGroup>
                    <Label for="exampleEmail">Email</Label>
                    <Input type="email" name="email" id="exampleEmail" placeholder="with a placeholder" />
                </FormGroup>            </ModalBody>
            <ModalFooter>
                <Button color="primary" onClick={props.toggleModal}> Confirm </Button>{' '}

            </ModalFooter>
        </div>

    )

}

export default EditProfileMenu