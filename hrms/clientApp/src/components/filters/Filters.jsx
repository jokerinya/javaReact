import React, { useState } from 'react';
import { Button, Icon, Header } from 'semantic-ui-react';
import CityFilter from './CityFilter';
import CompanyFilter from './CompanyFilter';
import PositionFilter from './PositionFilter';
import { useDispatch } from 'react-redux';
import { updateQueryParam } from '../../store/actions/jobQueryParamsActions';
import { useHistory } from 'react-router-dom';

export default function Filters() {
  const [cityId, setCityId] = useState('');
  const [companyId, setCompanyId] = useState('');
  const [positionId, setPositionId] = useState('');
  const dispatch = useDispatch();
  let history = useHistory();

  const handleCityId = (id) => {
    setCityId(id);
  };

  const handleCompanyId = (id) => {
    setCompanyId(id);
  };

  const handlePositionId = (id) => {
    setPositionId(id);
  };

  const handleClick = () => {
    let cityParam, companyParam, positionParam;

    cityParam = cityId ? `cityId=${cityId}&` : '';
    companyParam = companyId ? `companyId=${companyId}&` : '';
    positionParam = positionId ? `positionId=${positionId}&` : '';

    dispatch(updateQueryParam(`?${cityParam}${companyParam}${positionParam}`));
    history.push('/');
  };

  return (
    <>
      <Header as='h3'>
        <Icon name='filter' />
        <Header.Content>Jobs Filter</Header.Content>
      </Header>
      <h4>City</h4>
      <CityFilter getCityId={(id) => handleCityId(id)} />
      <h4>Position</h4>
      <PositionFilter getPositionId={(id) => handlePositionId(id)} />
      <h4>Company</h4>
      <CompanyFilter getCompanyId={(id) => handleCompanyId(id)} />
      <hr />
      <Button animated onClick={handleClick}>
        <Button.Content visible>Search Jobs</Button.Content>
        <Button.Content hidden>
          <Icon name='search' />
        </Button.Content>
      </Button>
    </>
  );
}
