import React from 'react';
import { Container, Grid, Segment } from 'semantic-ui-react';
import Filters from '../components/filters/Filters';
import MainBody from '../pages/MainBody';
import { Route, Switch } from 'react-router-dom';
import JobSeekerRegisterForm from '../pages/auth/JobSeekerRegisterForm';
import BaseRegisterForm from '../pages/auth/BaseRegisterForm';
import CompanyRegisterForm from '../pages/auth/CompanyRegisterForm';
import LoginForm from '../pages/auth/LoginForm';

export default function Home() {
  return (
    <>
      <Container className='my-m-1em fluid'>
        <Grid>
          <Grid.Row centered>
            <Switch>
              <Route exact path='/login' component={LoginForm} />
              <Route exact path='/register' component={BaseRegisterForm} />
              <Route
                exact
                path='/registerCompany'
                component={CompanyRegisterForm}
              />
              <Route
                exact
                path='/registerJobSeeker'
                component={JobSeekerRegisterForm}
              />
              <React.Fragment>
                <Grid.Column width={3}>
                  <Segment className={'deneme'}>
                    <Filters
                      style={{
                        background:
                          'linear-gradient( 45deg, black, transparent);',
                      }}
                    />
                  </Segment>
                </Grid.Column>
                <Grid.Column width={13}>
                  <MainBody />
                </Grid.Column>
              </React.Fragment>
            </Switch>
          </Grid.Row>
        </Grid>
      </Container>
    </>
  );
}
