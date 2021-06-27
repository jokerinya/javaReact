import React, { useState, useEffect } from 'react';
import CityService from '../../services/cityService';
import { useFormik } from 'formik';
import * as yup from 'yup';
import {
  Form,
  Button,
  Segment,
  Dropdown,
  Label,
  Grid,
  FormField,
  TextArea,
} from 'semantic-ui-react';
import PositionService from '../../services/positionService';
import TextEditorService from '../../utils/textFormatting/textEditorService';
import DateFormatter from '../../utils/dateFormatting/dateFormatter';
import JobPostingService from '../../services/jobPostingService';
import { useHistory } from 'react-router-dom';

function JobPostingAdd() {
  // Get Cities and positions at first
  const [cities, setCities] = useState([]);
  const [positions, setPositions] = useState([]);
  useEffect(() => {
    let cityService = new CityService();
    cityService.getAll().then((result) => {
      setCities(result.data.data);
    });
  }, [setCities]);
  useEffect(() => {
    let positionService = new PositionService();
    positionService.getAll().then((result) => {
      setPositions(result.data.data);
    });
  }, []);
  const history = useHistory();

  // Ready cities and positions for semantic ui dropdown
  const cityOptions = cities.map((city) => ({
    key: city.cityId,
    value: city.cityId,
    text: city.cityName,
  }));
  const positionOptions = positions.map((position, index) => ({
    key: index,
    value: position.positionName,
    text: TextEditorService.capitalize(position.positionName),
  }));

  const validationSchema = yup.object().shape({
    cityId: yup.string().required('Please select a city.'),
    positionName: yup.string().required('Please enter a position name.'),
    minWage: yup.string(),
    maxWage: yup.string(),
    remote: yup.boolean().required('Please select.'),
    partTime: yup.boolean().required('Please select.'),
    openPositions: yup.string().required('Please enter a number.'),
    lastApplicationDate: yup
      .date()
      .min(DateFormatter.getTodayString(), 'Cannot be before today.')
      .max(
        DateFormatter.getOneYearLaterString(),
        'Cannot be bigger than ' + DateFormatter.getOneYearLaterString()
      )
      .required('Please enter a date.'),
    description: yup
      .string()
      .min(20, 'Must be at least 20 chars.')
      .max(1000, 'Cannot be more than 1000 chars.')
      .required('Please enter some details.'),
  });
  const initialValues = {
    cityId: '',
    positionName: '',
    minWage: '',
    maxWage: '',
    remote: '',
    partTime: '',
    openPositions: '',
    lastApplicationDate: '',
    description: '',
  };
  const formik = useFormik({
    initialValues: initialValues,
    validationSchema: validationSchema,
    onSubmit: (values) => {
      handleSubmit(values);
    },
  });

  // Add an item to dropdown
  const handleAddItem = (field, value) => {
    formik.setFieldValue(field, value);
  };

  // prepare obj for axios
  const handleSubmit = (values) => {
    // city
    values.city = { cityId: values.cityId };
    delete values.cityId;
    // position
    values.position = { positionName: values.positionName };
    delete values.positionName;
    // minWage & maxWage
    values.minWage = values.minWage === '' ? 0 : values.minWage;
    values.maxWage = values.maxWage === '' ? 0 : values.maxWage;
    // Add userId
    let userId = 17;
    // obj is ready!
    console.log(values);
    const jobPostingService = new JobPostingService();
    jobPostingService.add(userId, values).then((result) => {
      console.log(result.data);
      alert(result.data.message);
      history.push('/');
    });
  };

  return (
    <>
      <h2 style={{ textAlign: 'center' }}>Add A Job Posting</h2>
      <Segment>
        <Form onSubmit={formik.handleSubmit}>
          {/* Position - City */}
          <Form.Field>
            <Grid stackable>
              <Grid.Row>
                <Grid.Column width={8}>
                  <label>Position</label>
                  <br />
                  <Dropdown
                    fluid
                    placeholder='Select Position or Type a new position'
                    clearable
                    search
                    selection
                    error={
                      !!formik.errors.positionName &&
                      formik.touched.positionName
                    }
                    allowAdditions
                    onBlur={(e, { name }) => formik.setFieldTouched(name)}
                    onAddItem={(e, data) => {
                      handleAddItem('positionName', data.value);
                      setPositions([
                        {
                          positionId: data.options.length + 1,
                          positionName: data.value,
                        },
                        ...positions,
                      ]);
                    }}
                    onChange={(e, { value }) => {
                      formik.setFieldValue('positionName', value);
                    }}
                    options={positionOptions}
                    name='positionName'
                    value={formik.values.positionName}
                  />
                  {!!formik.errors.positionName &&
                    formik.touched.positionName && (
                      <Label
                        pointing
                        basic
                        color='red'
                        content={formik.errors.positionName}
                      />
                    )}
                </Grid.Column>
                <Grid.Column width={8}>
                  <label>City</label>
                  <br />
                  <Dropdown
                    fluid
                    placeholder='Select City'
                    label='Select City'
                    clearable
                    search
                    selection
                    error={!!formik.errors.cityId && formik.touched.cityId}
                    onBlur={(e, { name }) => formik.setFieldTouched(name)}
                    onChange={(e, { value }) =>
                      formik.setFieldValue('cityId', value)
                    }
                    options={cityOptions}
                    name='cityId'
                    value={formik.values.cityId}
                  />
                  {!!formik.errors.cityId && formik.touched.cityId && (
                    <Label
                      pointing
                      basic
                      color='red'
                      content={formik.errors.cityId}
                    />
                  )}
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </Form.Field>
          {/* MinWage - MaxWage */}
          <Form.Field>
            <Grid stackable>
              <Grid.Row>
                <Grid.Column width={8}>
                  <Form.Input
                    icon='money'
                    iconPosition='left'
                    placeholder='Enter minimum wage.'
                    type='number'
                    name='minWage'
                    label='Minimum Wage'
                    onChange={formik.handleChange}
                    value={formik.values.minWage}
                    error={!!formik.errors.minWage && formik.touched.minWage}
                  />
                  {!!formik.errors.minWage && formik.touched.minWage && (
                    <Label
                      pointing
                      basic
                      color='red'
                      content={formik.errors.minWage}
                    />
                  )}
                </Grid.Column>
                <Grid.Column width={8}>
                  <Form.Input
                    icon='money'
                    iconPosition='left'
                    placeholder='Enter maximum wage.'
                    type='number'
                    name='maxWage'
                    label='Maximum Wage'
                    onChange={formik.handleChange}
                    value={formik.values.maxWage}
                    error={!!formik.errors.maxWage && formik.touched.maxWage}
                  />
                  {!!formik.errors.maxWage && formik.touched.maxWage && (
                    <Label
                      pointing
                      basic
                      color='red'
                      content={formik.errors.maxWage}
                    />
                  )}
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </Form.Field>
          {/* Remote - Full Time */}
          <Form.Field>
            <Grid stackable>
              <Grid.Row>
                <Grid.Column width={8}>
                  <label>Remote</label> <br />
                  <Dropdown
                    fluid
                    placeholder='Please select.'
                    name='remote'
                    label='Remote'
                    selection
                    error={!!formik.errors.remote && formik.touched.remote}
                    onBlur={(e, { name }) => formik.setFieldTouched(name)}
                    onChange={(e, { value }) =>
                      formik.setFieldValue('remote', value)
                    }
                    options={[
                      { key: 1, text: 'Remote', value: true },
                      { key: 2, text: 'In Office', value: false },
                    ]}
                    value={formik.values.remote}
                  />
                  {!!formik.errors.remote && formik.touched.remote && (
                    <Label
                      pointing
                      basic
                      color='red'
                      content={formik.errors.remote}
                    />
                  )}
                </Grid.Column>
                <Grid.Column width={8}>
                  <label>Part Time</label> <br />
                  <Dropdown
                    fluid
                    placeholder='Please select.'
                    name='partTime'
                    label='Part Time'
                    selection
                    error={!!formik.errors.partTime && formik.touched.partTime}
                    onBlur={(e, { name }) => formik.setFieldTouched(name)}
                    onChange={(e, { value }) =>
                      formik.setFieldValue('partTime', value)
                    }
                    options={[
                      { key: 1, text: 'Part Time', value: true },
                      { key: 2, text: 'Full Time', value: false },
                    ]}
                    value={formik.values.partTime}
                  />
                  {!!formik.errors.partTime && formik.touched.partTime && (
                    <Label
                      pointing
                      basic
                      color='red'
                      content={formik.errors.partTime}
                    />
                  )}
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </Form.Field>
          {/* Open Positions - Last Application Date */}
          <Form.Field>
            <Grid stackable>
              <Grid.Row>
                <Grid.Column width={8}>
                  <Form.Input
                    icon='user'
                    iconPosition='left'
                    placeholder='Enter open positions.'
                    type='number'
                    name='openPositions'
                    label='Open Positions'
                    onBlur={(e) => formik.setFieldTouched('openPositions')}
                    onChange={formik.handleChange}
                    value={formik.values.openPositions}
                    error={
                      !!formik.errors.openPositions &&
                      formik.touched.openPositions
                    }
                  />
                  {!!formik.errors.openPositions &&
                    formik.touched.openPositions && (
                      <Label
                        pointing
                        basic
                        color='red'
                        content={formik.errors.openPositions}
                      />
                    )}
                </Grid.Column>
                <Grid.Column width={8}>
                  <Form.Input
                    placeholder='Select Last Application Date'
                    type='date'
                    name='lastApplicationDate'
                    label='Last Application Date'
                    onBlur={(e) =>
                      formik.setFieldTouched('lastApplicationDate')
                    }
                    onChange={formik.handleChange}
                    value={formik.values.lastApplicationDate}
                    error={
                      !!formik.errors.lastApplicationDate &&
                      formik.touched.lastApplicationDate
                    }
                  />
                  {!!formik.errors.lastApplicationDate &&
                    formik.touched.lastApplicationDate && (
                      <Label
                        pointing
                        basic
                        color='red'
                        content={formik.errors.lastApplicationDate}
                      />
                    )}
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </Form.Field>
          {/* Description */}
          <FormField>
            <Grid stackable>
              <Grid.Row>
                <Grid.Column>
                  <TextArea
                    placeholder='Job Details'
                    name='description'
                    label='Open Positions'
                    onBlur={(e) => formik.setFieldTouched('description')}
                    onChange={formik.handleChange}
                    value={formik.values.description}
                  />
                  {!!formik.errors.description &&
                    formik.touched.description && (
                      <Label
                        pointing
                        basic
                        color='red'
                        content={formik.errors.description}
                      />
                    )}
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </FormField>
          <Button color='green' type='submit'>
            ADD
          </Button>
        </Form>
      </Segment>
    </>
  );
}

export default JobPostingAdd;
