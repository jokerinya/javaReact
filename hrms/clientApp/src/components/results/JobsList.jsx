import React, { useState, useEffect } from 'react';
import JobPostingService from '../../services/jobPostingService';
import { Icon, Menu, Table } from 'semantic-ui-react';
import TextEditorService from '../../utils/textEditorService';

export default function JobsList() {
  const [jobPostings, setJobPostings] = useState([]);

  useEffect(() => {
    let jobPostingService = new JobPostingService();
    jobPostingService.getJobPostingsAll().then((result) => {
      setJobPostings(result.data.data);
    });
  }, []);

  return (
    <>
      <Table celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Company Name</Table.HeaderCell>
            <Table.HeaderCell>Position Name</Table.HeaderCell>
            <Table.HeaderCell>City</Table.HeaderCell>
            <Table.HeaderCell>Description</Table.HeaderCell>
            <Table.HeaderCell>Min Wage</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {/* js */}
          {jobPostings.map((jobPosting) => (
            <Table.Row key={jobPosting.jobPostingId}>
              <Table.Cell>
                {TextEditorService.capitalize(jobPosting.company.companyName)}
              </Table.Cell>
              <Table.Cell>
                {TextEditorService.capitalize(jobPosting.position.positionName)}
              </Table.Cell>
              <Table.Cell>{jobPosting.city.cityName}</Table.Cell>
              <Table.Cell>{jobPosting.description}</Table.Cell>
              <Table.Cell>₺{jobPosting.minWage}</Table.Cell>
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
