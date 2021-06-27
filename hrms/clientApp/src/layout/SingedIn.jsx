import React from 'react';
import { Dropdown, Image, Menu } from 'semantic-ui-react';
import { useHistory } from 'react-router-dom';

export default function SingedIn({ signOut }) {
  let history = useHistory();
  const handleJobAddClick = () => {
    history.push('/jobs/add');
  };
  return (
    <>
      <Menu.Item>
        <Image
          avatar
          spaced='right'
          src='https://res.cloudinary.com/jokerinya/image/upload/v1622934942/yc6t1cjg1ql8eaguoi2a.png'
        />
        <Dropdown pointing='top left' text='Jokerinya'>
          <Dropdown.Menu>
            <Dropdown.Item text='My Info' icon='info' />
            <Dropdown.Item
              text='Add Job Posting'
              icon='add'
              onClick={handleJobAddClick}
            />
            <Dropdown.Item text='Sign-Out' icon='sign-out' onClick={signOut} />
          </Dropdown.Menu>
        </Dropdown>
      </Menu.Item>
    </>
  );
}
