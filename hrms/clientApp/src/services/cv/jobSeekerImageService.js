import axios from 'axios';

export default class JobSeekerImageService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobSeekerImage/${userId}`
    );
  }

  add(userId, photo) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobSeekerImage/${userId}/add`,
      photo
    );
  }

  update(userId, photo) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobSeekerImage/${userId}/update`,
      photo
    );
  }

  delete(userId, photo) {
    return axios.delete(
      `${process.env.REACT_APP_BACKEND_API_URL}/jobSeekerImage/${userId}/delete`
    );
  }
}
