import axios from 'axios';

export default class CVService {
  getJobSeekerCv(userId) {
    return axios.get(`${process.env.REACT_APP_BACKEND_API_URL}/cv/${userId}`);
  }
}
