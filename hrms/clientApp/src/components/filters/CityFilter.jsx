import React, { useEffect, useState } from 'react';
import { Dropdown } from 'semantic-ui-react';
import CityService from '../../services/cityService';

export default function CityFilter(props) {
  const [cities, setCities] = useState([]);

  const cityOptions = cities.map((city) => ({
    key: city.cityId,
    value: city.cityId,
    text: city.cityName,
  }));

  useEffect(() => {
    let cityService = new CityService();
    cityService.getAll().then((result) => {
      setCities(result.data.data);
    });
  }, []);

  function getCityId(event, data) {
    // Select city id, later we will send it to redux for re render
    props.getCityId(data.value);
  }

  return (
    <>
      <Dropdown
        placeholder='Select City'
        clearable
        fluid
        search
        selection
        onChange={getCityId}
        options={cityOptions}
      />
    </>
  );
}
