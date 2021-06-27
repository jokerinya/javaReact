import React from 'react';
import JobsList from './jobPosting/JobsList';
import CompaniesList from './company/CompaniesList';
import { Route, Switch } from 'react-router-dom';
import JobPostingDetail from './jobPosting/JobPostingDetail';
import CompanyDetail from './company/CompanyDetail';
import NotFound from './NotFound';
import JobPostingAdd from './jobPosting/JobPostingAdd';
import JobSeekersList from './jobSeeker/JobSeekersList';
import JobSeekerDetail from './jobSeeker/cv/JobSeekerDetail';
import Footer from '../layout/Footer';

export default function MainBody() {
  return (
    <>
      <Switch>
        <Route exact path='/' component={JobsList} />
        <Route exact path='/companies' component={CompaniesList} />
        <Route exact path='/companies/:id' component={CompanyDetail} />
        <Route exact path='/jobs' component={JobsList} />
        <Route path='/jobs/add' component={JobPostingAdd} />
        <Route exact path='/jobs/:id' component={JobPostingDetail} />
        <Route exact path='/jobSeekers' component={JobSeekersList} />
        <Route exact path='/jobSeekers/:id' component={JobSeekerDetail} />
        <Route path='*' component={NotFound} />
      </Switch>
      <Footer />
    </>
  );
}
