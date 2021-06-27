import React from 'react';
import { Button, Icon } from 'semantic-ui-react';
import { useHistory } from 'react-router-dom';

function BaseRegisterForm() {
  let history = useHistory();

  const handleEmployerClick = () => {
    history.push('/registerCompany');
  };

  const handleJobSeekerClick = () => {
    history.push('/registerJobSeeker');
  };

  return (
    <>
      <Button.Group>
        <Button color={'blue'} onClick={handleEmployerClick}>
          <Icon name={'factory'} />
          I'm an Employer
        </Button>
        <Button.Or text='or' />
        <Button positive onClick={handleJobSeekerClick}>
          <Icon name={'user'} />
          I'm a Job Seeker
        </Button>
      </Button.Group>
    </>
  );
}

export default BaseRegisterForm;
