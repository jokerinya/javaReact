import React from 'react';
import { List, Icon, Card, Label } from 'semantic-ui-react';

export default function CVSchools({ schools }) {
  return (
    <Card.Description>
      <List celled>
        {schools?.length > 0 ? (
          schools.map((school) => (
            <List.Item key={school.graduatedSchoolId}>
              <Icon name={'building'} size={'big'} />
              <List.Content>
                <List.Header style={{ textTransform: 'capitalize' }}>
                  {school.school?.schoolName}
                </List.Header>
                Departmant:{' '}
                <Label style={{ textTransform: 'capitalize' }} color={'olive'}>
                  {school.department?.departmentName}
                </Label>
                <br />
                End Date:{' '}
                <span>
                  {school.graduationDate
                    ? school.graduationDate
                    : 'Still Studing'}
                </span>{' '}
                <br />
                Start Date: <span>{school.startDate}</span> <br />
              </List.Content>
            </List.Item>
          ))
        ) : (
          <List.Item>No Education Yet!</List.Item>
        )}
      </List>
    </Card.Description>
  );
}
