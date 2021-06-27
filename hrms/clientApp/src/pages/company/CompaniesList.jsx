import React, { useState, useEffect } from 'react';
import { Button, Icon, Menu, Table } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import CompanyService from '../../services/companyService';

function CompaniesList() {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    let companyService = new CompanyService();
    companyService.getAll().then((result) => {
      setCompanies(result.data.data);
    });
  }, []);

  return (
    <>
      <h2 style={{ textAlign: 'center' }}>Companies List</h2>
      <Table celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Company Name</Table.HeaderCell>
            <Table.HeaderCell>Web Site</Table.HeaderCell>
            <Table.HeaderCell>Email</Table.HeaderCell>
            <Table.HeaderCell>Details</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {/* js */}
          {companies.map((company) => (
            <Table.Row key={company.userId}>
              <Table.Cell style={{ textTransform: 'capitalize' }}>
                {company.companyName}
              </Table.Cell>
              <Table.Cell>{company.companyWebsite}</Table.Cell>
              <Table.Cell>{company.email}</Table.Cell>
              <Table.Cell>
                <Link to={`/companies/${company.userId}`}>
                  <Button icon={'magnify'} />
                </Link>
              </Table.Cell>
            </Table.Row>
          ))}
        </Table.Body>

        <Table.Footer>
          <Table.Row>
            <Table.HeaderCell colSpan='5'>
              <Menu floated='right' pagination>
                <Menu.Item as='a' icon>
                  <Icon name='chevron left' />
                </Menu.Item>
                <Menu.Item as='a'>1</Menu.Item>
                <Menu.Item as='a'>2</Menu.Item>
                <Menu.Item as='a'>3</Menu.Item>
                <Menu.Item as='a'>4</Menu.Item>
                <Menu.Item as='a' icon>
                  <Icon name='chevron right' />
                </Menu.Item>
              </Menu>
            </Table.HeaderCell>
          </Table.Row>
        </Table.Footer>
      </Table>
    </>
  );
}

export default CompaniesList;
