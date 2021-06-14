import axios from 'axios';

export default class JobPostingService {
  getJobPostingsAll() {
    return axios.get(`${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/getAllActiveSortedByDate`);
  }
  getAllActiveWithCompanyId(userId){
    return axios.get(`${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/getAllActive`);
  }
  setPassive(userId, jobPostingId){
    return axios.get(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/setPassive?jobPostingId=${jobPostingId}`
    );
  }

  /**
   *Add-Update Demo Obj
{
  "active": true,
  "city": {
    "cityId": 34
  },
  "description": "UX designer for our awesome projects.",
  "lastApplicationDate": "2021-08-01",
  "maxWage": 5400,
  "minWage": 4400,
  "openPositions": 1,
  "position": {
    "positionName": "ux designer"
  }
}
   */
  add(userId, jobPosting){
    return axios.post(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/add`, jobPosting
    );
  }
  update(userId, jobPostingId, jobPosting){
    return axios.put(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/update/${jobPostingId}`, jobPosting
    );
  }
  delete(userId, jobPostingId){
    return axios.delete(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobPostings/${userId}/delete/${jobPostingId}`
    );
  }
}