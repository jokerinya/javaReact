import React, { useEffect, useState } from 'react';
import { Card, Icon, Button, List } from 'semantic-ui-react';

function CvSocialMedia({ address }) {
  const [github, setGithub] = useState('');
  const [linkedIn, setLinkedIn] = useState('');

  useEffect(() => {
    setGithub(address?.githubAddress);
    setLinkedIn(address?.linkedinAddress);
  }, [address?.githubAddress, address?.linkedinAddress]);

  const empty = () => {
    if (!address) {
      return <List.Item>No Social Media Platforms!</List.Item>;
    }
  };

  return (
    <Card.Description>
      <List celled>
        {empty()}
        {github && (
          <Button color='grey' as={'a'} target='_blank' href={github}>
            <Icon name='github' /> Github
          </Button>
        )}
        {linkedIn && (
          <Button color='linkedin' as={'a'} target='_blank' href={linkedIn}>
            <Icon name='linkedin' /> LinkedIn
          </Button>
        )}
      </List>
    </Card.Description>
  );
}

export default CvSocialMedia;
