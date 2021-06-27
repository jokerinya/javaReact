import React, { useState, useEffect } from 'react';
import JobSeekerService from '../../services/jobSeekerService';
import { Button, Icon, Image, Menu, Table } from 'semantic-ui-react';
import { Link } from 'react-router-dom';

function JobSeekersList() {
  const [jobSeekers, setJobSeekers] = useState([]);
  useEffect(() => {
    let jobSeekerService = new JobSeekerService();
    jobSeekerService.getAll().then((result) => setJobSeekers(result.data.data));
  }, [setJobSeekers]);

  return (
    <>
      <h2 style={{ textAlign: 'center' }}>Job Seekers</h2>
      <Table celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Image</Table.HeaderCell>
            <Table.HeaderCell>First Name</Table.HeaderCell>
            <Table.HeaderCell>Last Name</Table.HeaderCell>
            <Table.HeaderCell>Year of Birth</Table.HeaderCell>
            <Table.HeaderCell>Details & CV</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {/* js */}
          {jobSeekers.map((jobSeeker) => (
            <Table.Row key={jobSeeker.userId}>
              <Table.Cell>
                {jobSeeker.jobSeekerImage?.imageUrl ? (
                  <Image avatar src={jobSeeker.jobSeekerImage?.imageUrl} />
                ) : (
                  <Icon name={'user'} circular />
                )}
              </Table.Cell>
              <Table.Cell style={{ textTransform: 'capitalize' }}>
                {jobSeeker.firstName}
              </Table.Cell>
              <Table.Cell style={{ textTransform: 'upperCase' }}>
                {jobSeeker.lastName}
              </Table.Cell>
              <Table.Cell>{jobSeeker.yearOfBirth}</Table.Cell>
              <Table.Cell>
                <Link to={`/jobSeekers/${jobSeeker.userId}`}>
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

export default JobSeekersList;
