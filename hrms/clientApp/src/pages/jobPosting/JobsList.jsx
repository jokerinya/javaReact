import React, { useState, useEffect } from 'react';
import JobPostingService from '../../services/jobPostingService';
import { Button, Icon, Menu, Table } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import EmptyResult from '../../components/filters/EmptyResult';

export default function JobsList() {
  const [jobPostings, setJobPostings] = useState([]);
  const { jobQueryParams } = useSelector((state) => state.jobQueryParams);

  useEffect(() => {
    let jobPostingService = new JobPostingService();
    jobPostingService.getAllActiveFiltered(jobQueryParams).then((result) => {
      setJobPostings(result.data.data);
    });
  }, [setJobPostings, jobQueryParams]);

  return (
    <>
      {!jobPostings.length ? (
        <EmptyResult />
      ) : (
        <>
          <h2 style={{ textAlign: 'center' }}>Jobs List</h2>
          <Table celled>
            <Table.Header>
              <Table.Row>
                <Table.HeaderCell>Position Name</Table.HeaderCell>
                <Table.HeaderCell>Company Name</Table.HeaderCell>
                <Table.HeaderCell>City</Table.HeaderCell>
                <Table.HeaderCell>Type</Table.HeaderCell>
                <Table.HeaderCell>Remote</Table.HeaderCell>
                <Table.HeaderCell>Details</Table.HeaderCell>
              </Table.Row>
            </Table.Header>

            <Table.Body>
              {/* js */}
              {jobPostings.map((jobPosting) => (
                <Table.Row key={jobPosting.jobPostingId}>
                  <Table.Cell style={{ textTransform: 'capitalize' }}>
                    {jobPosting.position.positionName}
                  </Table.Cell>
                  <Table.Cell style={{ textTransform: 'capitalize' }}>
                    {jobPosting.company.companyName}
                  </Table.Cell>
                  <Table.Cell>{jobPosting.city.cityName}</Table.Cell>
                  <Table.Cell>
                    {jobPosting.partTime ? 'Part Time' : 'Full Time'}
                  </Table.Cell>
                  <Table.Cell>
                    {jobPosting.remote ? 'Remote' : 'In office'}
                  </Table.Cell>
                  <Table.Cell>
                    <Link to={`/jobs/${jobPosting.jobPostingId}`}>
                      <Button icon={'magnify'} />
                    </Link>
                  </Table.Cell>
                </Table.Row>
              ))}
            </Table.Body>

            <Table.Footer>
              <Table.Row>
                <Table.HeaderCell colSpan='6'>
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
      )}
    </>
  );
}
