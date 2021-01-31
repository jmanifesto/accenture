import React, {Component, useState} from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import {Button, Form, FormGroup, Label, Input, Container, Col, Row, InputGroup, InputGroupAddon} from 'reactstrap';
import useFetchData from "./hooks/useFetchData";

export const Home = () => {
    const { sendRequest, isSending, data } = useFetchData('api/url');

    const setRSelected = () => {
        sendRequest({
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                url: url,
                user: user,
            }),
        })
    }
    const [url, setUrl] = useState("");
    const [user, setUser] = useState("");
    const handleChange = (event, callback) => {
        callback(event.target.value)
    }

    return (
        <div>
            <AppNavbar/>
            <Container>
                <Form>
                    <Row form>
                        <Col md={6}>
                            <FormGroup>
                                <Label for="URL">URL</Label>
                                <Input
                                    valid
                                    type="url"
                                    name="url"
                                    id="url"
                                    placeholder="Bitte geben Sie die URL ein"
                                    onChange={(event) => handleChange(event, setUrl)}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label for="name">Benutzer</Label>
                                <Input
                                    type="text"
                                    name="user"
                                    id="user"
                                    onChange={(event) => handleChange(event, setUser)}
                                />
                            </FormGroup>
                        </Col>
                        <Col md={6}>
                        </Col>
                    </Row>
                </Form>
                <Form>
                    <Row form>
                        <Col md={2}>
                            <Button onClick={setRSelected} >Verkleinern</Button>
                        </Col>
                        <Col md={4}>
                            <FormGroup>
                                <InputGroup>
                                    <Input
                                        value={data.urlId}
                                        disabled
                                    />
                                    <InputGroupAddon addonType="append">
                                        <Button color="secondary">Speichern</Button>
                                    </InputGroupAddon>
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