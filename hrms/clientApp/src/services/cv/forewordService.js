import axios from 'axios';

export default class ForewordService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/forewords/${userId}`
    );
  }

  add(userId, foreword) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/forewords/${userId}/add`,
      foreword
    );
  }

  delete(userId, foreword) {
    return axios.delete(
      `${process.env.REACT_APP_BACKEND_API_URL}/forewords/${userId}/delete`,
      { data: foreword }
    );
  }

  update(userId, foreword) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/forewords/${userId}/update`,
      foreword
    );
  }
}
