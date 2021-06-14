import axios from 'axios';

export default class TechnologyService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/technologies/${userId}`
    );
  }

  // use for update too.
  addToJobSeeker(userId, technology) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/technologies/${userId}/add`,
      technology
    );
  }

  removeFromJobSeeker(userId, technology) {
    return axios.delete(
      `${process.env.REACT_APP_BACKEND_API_URL}/technologies/${userId}/delete`,
      { data: technology }
    );
  }
}

/*
 * Obj Format
{
  "technologyId": 1,
  "technologyName": "react"
}
*/

/**
 *  Ornek Fonksiyon
 * 
 * function deneme() {
    const tech = {
      technologyId: 1,
      technologyName: 'react',
    };
    const technologyService = new TechnologyService();
    technologyService
      .removeFromJobSeeker(5, tech)
      .then((result) => {
        console.log(result.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }
 * 
 */
