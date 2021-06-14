import axios from 'axios';

export default class JobSeekerService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobseekers/${userId}`
    );
  }

  /**
   * Add demo obj
{
  "email": "user334@users.com",
  "firstName": "ibrahim",
  "lastName": "Şakacı",
  "nationalIdentityNo": "68863159792",
  "password": "something",
  "yearOfBirth": 1989
}
   */
  add(jobSeeker){
    return axios.post(`${process.env.REACT_APP_BACKEND_API_URL}/jobseekers/add`, jobSeeker);
  }

  updateEmail(userId, email){
    return axios.post(`${process.env.REACT_APP_BACKEND_API_URL}/jobseekers/${userId}/updateEmail`, email);
  }

  updatePassword(userId, password){
    return axios.post(`${process.env.REACT_APP_BACKEND_API_URL}/jobseekers/${userId}/updateEmail`, password);
  }

}
