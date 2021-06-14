import React from 'react';
import JobsList from './results/JobsList';

export default function MainBody() {
  return (
    <>
      {/* will toggle between joblist and companylist */}
      <JobsList />
    </>
  );
}
