import React from 'react';
import { Link } from 'react-router-dom';

function NotFound(props) {
  return (
    <div>
      There is nothing here turn back to <Link to={'/'}>Main Page</Link>
    </div>
  );
}

export default NotFound;
