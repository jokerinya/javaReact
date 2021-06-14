import React, { useState } from 'react';
import { Button, Menu, Icon, Container } from 'semantic-ui-react';

export default function Header() {
  const [activeItem, setActiveItem] = useState('home');
  return (
    <Container>
      <Menu size='large'>
        <Menu.Item
          name='home'
          active={activeItem === 'home'}
          onClick={() => setActiveItem('home')}
        />
        <Menu.Item
          name='job search'
          active={activeItem === 'job search'}
          onClick={() => setActiveItem('job search')}
        />
        <Menu.Menu position='right'>
          <Menu.Item>
            <Button primary>
              <Icon name='address card' />
              Sign Up
            </Button>
          </Menu.Item>
          <Menu.Item>
            <Button primary>
              <Icon name='pencil alternate' />
              Login
            </Button>
          </Menu.Item>
        </Menu.Menu>
      </Menu>
    </Container>
  );
}
