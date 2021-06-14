import React from 'react';
import { Container, Icon } from 'semantic-ui-react';

export default function Footer() {
  return (
    <Container>
      <div className='my-footer'>
        <hr />
        <span>Made by Jokerinya-2021</span>
        <a
          href='http://github.com/jokerinya2013'
          target='_blank'
          rel='noopener noreferrer'
          className='my-footer-link mr-2'
        >
          <Icon name='github' size='big' />
        </a>
        <a
          href='http://www.linkedin.com/in/jokerinya'
          target='_blank'
          rel='noopener noreferrer'
          className='my-footer-link mr-2'
        >
          <Icon name='linkedin' size='big' link />
        </a>
      </div>
    </Container>
  );
}
