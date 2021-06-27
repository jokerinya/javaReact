import React from 'react';
import * as Yup from 'yup';
import { Formik, Form } from 'formik';
import BaseTextInput from '../../utils/customFormControllers/BaseTextInput';
import { Button, Message, Segment } from 'semantic-ui-react';
import { Link } from 'react-router-dom';

function LoginForm(props) {
  // Define Schema with Yup
  const LogInSchema = Yup.object().shape({
    email: Yup.string().email().required('Email is required'),
    password: Yup.string()
      .required('Password is required')
      .min(6, 'Password is too short - should be 6 chars minimum'),
  });
  // Define initial values
  const initialValues = {
    email: '',
    password: '',
  };
  // Handle Submit
  const handleSubmit = (values) => {
    console.log(values);
    alert(JSON.stringify(values));
  };

  return (
    <div>
      <h2>Login</h2>
      <Segment>
        <Formik
          initialValues={initialValues}
          validationSchema={LogInSchema}
          onSubmit={handleSubmit}
        >
          <Form className='ui form'>
            <BaseTextInput
              fluid
              icon='user'
              iconPosition='left'
              placeholder='E-mail address'
              name='email'
              label='Email'
            />
            <BaseTextInput
              fluid
              icon='lock'
              iconPosition='left'
              placeholder='Password'
              type='password'
              name='password'
              label='Password'
            />
            <Button color='green' fluid type='submit'>
              Submit
            </Button>
          </Form>
        </Formik>
      </Segment>
      <Message info>
        Don't have an account?{' '}
        <b>
          <Link to={'/register'}>Register Now</Link>
        </b>
      </Message>
    </div>
  );
}

export default LoginForm;
