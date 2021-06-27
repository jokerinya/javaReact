import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Menu, Container } from 'semantic-ui-react';
import SingedIn from './SingedIn';
import SignedOut from './SignedOut';

export default function Header() {
  const [activeItem, setActiveItem] = useState('home');
  const [auth, setAuth] = useState(false);
  const history = useHistory();

  const handleHomeClick = () => {
    setActiveItem('home');
    history.push('/');
  };

  const handleCompaniesClick = () => {
    setActiveItem('companies');
    history.push('/companies');
  };

  const handleJobSeekerClick = () => {
    setActiveItem('jobSeekers');
    history.push('/jobSeekers');
  };

  const handleLoginClick = () => {
    setAuth(true);
    history.push('/login');
  };

  const handleRegisterClick = () => {
    setAuth(true);
    history.push('/register');
  };

  const handleSignOut = () => {
    setAuth(false);
    history.replace('/');
  };

  return (
    <Container>
      <Menu size='large' stackable>
        <Menu.Item
          name='Jobs'
          active={activeItem === 'home'}
          onClick={() => handleHomeClick()}
        />
        <Menu.Item
          name='Job Seekers'
          active={activeItem === 'jobSeekers'}
          onClick={() => handleJobSeekerClick()}
        />
        <Menu.Item
          name='Companies'
          active={activeItem === 'companies'}
          onClick={() => handleCompaniesClick()}
        />
        <Menu.Menu position='right'>
          {auth ? (
            <SingedIn signOut={handleSignOut} />
          ) : (
            <SignedOut
              login={handleLoginClick}
              register={handleRegisterClick}
            />
          )}
        </Menu.Menu>
      </Menu>
    </Container>
  );
}
