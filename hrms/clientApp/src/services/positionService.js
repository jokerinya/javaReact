import axios from 'axios';

export default class PositionService {
  getAll() {
    return axios.get(`${process.env.REACT_APP_BACKEND_API_URL}/jobs/getall`);
  }
}
