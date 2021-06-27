import React from 'react';
import { List, Icon, Card, Label } from 'semantic-ui-react';

export default function CVExperiences({ experiences }) {
  return (
    <Card.Description>
      <List celled>
        {experiences?.length > 0 ? (
          experiences.map((experience) => (
            <List.Item key={experience.jobExperienceId}>
              <Icon name={'factory'} size={'big'} />
              <List.Content>
                <List.Header style={{ textTransform: 'capitalize' }}>
                  {experience.companyName}
                </List.Header>
                Position:{' '}
                <Label style={{ textTransform: 'capitalize' }} color={'green'}>
                  {experience.position?.positionName}
                </Label>
                <br />
                End Date:{' '}
                <span>
                  {experience.endDate ? experience.endDate : 'Still Working'}
                </span>{' '}
                <br />
                Start Date: <span>{experience.startDate}</span> <br />
              </List.Content>
            </List.Item>
          ))
        ) : (
          <List.Item>No Experience Yet!</List.Item>
        )}
      </List>
    </Card.Description>
  );
}
