import { jobQueryParams } from '../intialValues/jobQueryParams';
import { JOB_QUERY_PARAMS_UPDATE } from '../actions/jobQueryParamsActions';

const initialState = {
  jobQueryParams,
};

export default function jobQueryParamsReducer(
  state = initialState,
  { type, payload }
) {
  switch (type) {
    case JOB_QUERY_PARAMS_UPDATE:
      return {
        ...state,
        jobQueryParams: payload,
      }; // new state obj (reference change)
    default:
      return state;
  }
}
