export const JOB_QUERY_PARAMS_UPDATE = 'JOB_QUERY_PARAMS_UPDATE';

export function updateQueryParam(queryParams) {
  return {
    type: JOB_QUERY_PARAMS_UPDATE,
    payload: queryParams,
  };
}
