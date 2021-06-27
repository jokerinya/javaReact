import axios from 'axios';

export default class SocialMediaService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/socialMediaAddress/${userId}`
    );
  }

  /**
   * Add-Update sample obj
{
  "githubAddress": "jokerinya2013",
  "linkedinAddress": "jokerinya"
}
   */

  add(userId, socialMediaAddresses) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/socialMediaAddress/${userId}/add`,
      socialMediaAddresses
    );
  }
  update(userId, socialMediaAddresses) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/socialMediaAddress/${userId}/update`,
      socialMediaAddresses
    );
  }
  delete(userId) {
    return axios.delete(
      `${process.env.REACT_APP_BACKEND_API_URL}/socialMediaAddress/${userId}/update`
    );
  }
}
