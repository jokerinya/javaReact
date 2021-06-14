import React from 'react';
import { Container, Grid, Segment } from 'semantic-ui-react';
import Filters from '../components/filters/Filters';
import MainBody from '../components/MainBody';

export default function Home() {
  return (
    <Container className='my-m-1em fluid'>
      <Grid>
        <Grid.Row>
          <Grid.Column width={4}>
            <Segment>
              <Filters />
            </Segment>
          </Grid.Column>
          <Grid.Column width={12}>
            <MainBody />
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
}
