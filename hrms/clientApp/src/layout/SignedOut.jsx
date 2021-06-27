import React from 'react';
import { Button, Icon, Menu } from 'semantic-ui-react';

function SignedOut({ login, register }) {
  return (
    <>
      <Menu.Item>
        <Button primary onClick={register}>
          <Icon name='address card' />
          Sign Up
        </Button>
      </Menu.Item>
      <Menu.Item>
        <Button primary onClick={login}>
          <Icon name='pencil alternate' />
          Login
        </Button>
      </Menu.Item>
    </>
  );
}

export default SignedOut;
