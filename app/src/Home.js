import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import {Button, Form, FormGroup, Label, Input, Container, Col, Row, InputGroup, InputGroupAddon} from 'reactstrap';

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container>
                    <Form>
                        <Row form>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="URL">URL</Label>
                                    <Input type="url" name="url" id="url" placeholder="Bitte geben Sie die URL ein" />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="name">Name</Label>
                                    <Input type="text" name="user" id="user"/>
                                </FormGroup>
                            </Col>
                            <Col md={6}>
                            </Col>
                        </Row>
                    </Form>
                    <Form>
                        <Row form>
                            <Col md={2}>
                                <Button>Verkleinern</Button>
                            </Col>
                            <Col md={4}>
                                <FormGroup>
                                    <InputGroup>
                                        <Input disabled/>
                                        <InputGroupAddon addonType="append"><Button color="secondary">Speichern</Button></InputGroupAddon>
                                    </InputGroup>
                                </FormGroup>
                            </Col>
                    </Row>
                    </Form>

                    <Button color="link"><Link to="/urls">URLs bearbeiten</Link></Button>
                </Container>
            </div>
        );
    }
}

export default Home;