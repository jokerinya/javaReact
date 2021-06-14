import axios from 'axios';

export default class CityService {
  getAll() {
    return axios.get(`${process.env.REACT_APP_BACKEND_API_URL}/cities/getAll`);
  }
}
