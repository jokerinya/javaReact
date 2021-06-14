import axios from 'axios';

export default class JobExperienceService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobExperiences/${userId}/getByJobSeekerIdOrderByEndDate`
    );
  }

  /**
   * Add sample Object
{
  "companyName": "Tesla",
  "endDate": null,
  "position": {
    "positionName": "Project Manager"
  },
  "startDate": "2021-12-09"
}
   */
  add(userId, jobExperience){
    return axios.post(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobExperiences/${userId}/add`, jobExperience)
  }

  delete(userId, jobExperience){
    return axios.delete(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobExperiences/${userId}/delete`, {data: jobExperience})
  }

  /**
   * Update format
{
  "companyName": "abc",
  "endDate": "2021-04-04",
  "jobExperienceId": 4,
  "position": {
    "positionName": "DevOps Engineer"
  },
  "startDate": "2021-03-01"
}
   */

  update(userId, jobExperience){
    return axios.put(
        `${process.env.REACT_APP_BACKEND_API_URL}/jobExperiences/${userId}/update`, jobExperience
    )
  }
}
