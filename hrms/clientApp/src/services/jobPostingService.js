import axios from 'axios';

export default class JobPostingService {
  getJobPostingsAll() {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/getAllActiveSortedByDate`
    );
  }
  getById(jobPostingId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${jobPostingId}`
    );
  }
  getAllActiveWithCompanyId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/getAllActive`
    );
  }
  getAllActiveFiltered(queryParams) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/getAllFiltered${queryParams}`
    );
  }
  setPassive(userId, jobPostingId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/setPassive?jobPostingId=${jobPostingId}`
    );
  }

  /**
   *Add-Update Demo Obj
   *
{
  "city": {
    "cityId": 34
  },
  "description": "Sales manager for our awesome company",
  "lastApplicationDate": "2021-08-08",
  "maxWage": 4000,
  "minWage": 3500,
  "openPositions": 2,
  "partTime": true,
  "position": {
    "positionName": "sales manager"
  },
  "remote": false
}
   */
  add(userId, jobPosting) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/add`,
      jobPosting
    );
  }
  update(userId, jobPostingId, jobPosting) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/update/${jobPostingId}`,
      jobPosting
    );
  }
  delete(userId, jobPostingId) {
    return axios.delete(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/delete/${jobPostingId}`
    );
  }
}
