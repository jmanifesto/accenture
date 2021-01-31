import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class GroupList extends Component {

    constructor(props) {
        super(props);
        this.state = {groups: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/urls')
            .then(response => response.json())
            .then(data => this.setState({groups: data, isLoading: false}));
    }

    async remove(url) {
        await fetch(`/api/url/${url.id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(url),
        }).then(() => {
            let updatedGroups = [...this.state.groups].filter(i => i.id !== url.id);
            this.setState({groups: updatedGroups});
        });
    }

    render() {
        const {groups, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        /*
        id: 1 eindeutig
        url: "www.google.de" lange addresse
        urlId: "google"  kleine Addresse
        user: "jleyria"    Benutzer
         */
        const urlList = groups.map(URL => {
            //group.user
            //es fehlt der group user
            return <tr key={URL.id}>
                <td style={{whiteSpace: 'nowrap'}}>{URL.url}</td>
                <td>{URL.urlId}</td>
                <td>{URL.user}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/urls/" + URL.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(URL)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/urls/new">URL Addieren</Button>
                    </div>
                    <h3>URL Shortener</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">URL</th>
                            <th width="20%">Verkürzte URL</th>
                            <th width="10%">Benutzer</th>
                            <th width="10%">Aktionen</th>
                        </tr>
                        </thead>
                        <tbody>
                        {urlList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default GroupList;