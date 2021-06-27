import React from 'react';
import { useField } from 'formik';
import { FormField, FormInput, Label } from 'semantic-ui-react';

function BaseTextInput({ ...props }) {
  const [field, meta] = useField(props);

  return (
    <FormField error={meta.touched && !!meta.error}>
      <FormInput {...props} {...field} />
      {meta.touched && !!meta.error ? (
        <Label pointing basic color='red' content={meta.error} />
      ) : null}
    </FormField>
  );
}

export default BaseTextInput;
