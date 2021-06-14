import React from 'react';
import { Button, Icon } from 'semantic-ui-react';
import CityFilter from './CityFilter';
import CompanyFilter from './CompanyFilter';
import PositionFilter from './PositionFilter';

export default function Filters() {
  return (
    <>
      <h4>City</h4>
      <CityFilter />
      <h4>Position</h4>
      <PositionFilter />
      <h4>Company</h4>
      <CompanyFilter />
      <hr />
      <Button animated>
        <Button.Content visible>Search</Button.Content>
        <Button.Content hidden>
          <Icon name='search' />
        </Button.Content>
      </Button>
    </>
  );
}
