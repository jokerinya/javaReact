import { combineReducers } from 'redux';
import jobQueryParamsReducer from './reducers/jobQueryParamsReducer';

const rootReducer = combineReducers({
  jobQueryParams: jobQueryParamsReducer,
});

export default rootReducer;
