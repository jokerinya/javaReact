import axios from 'axios';

export default class KnownLanguageService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/knownLanguages/${userId}`
    );
  }

  /**
   * Add Demo Obj
{
  "language": {
    "languageName": "German"
  },
  "languageLevel": 4
}
   */

  add(userId, knownLanguage) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/knownLanguages/${userId}/add`,
      knownLanguage
    );
  }

  /**
   * Delete Demo Obj
{
  "knownLanguageId": 4,
  "language": {
    "languageName": "string"
  },
  "languageLevel": 1
}
   */

  delete(userId, knownLanguage) {
    return axios.delete(
      `${process.env.REACT_APP_BACKEND_API_URL}/knownLanguages/${userId}/delete`,
      { data: knownLanguage }
    );
  }

  /**
   * Sample obj for update
{
  "knownLanguageId": 1,
  "language": {
    "languageName": "dutch"
  },
  "languageLevel": 2
}
   */
  update(userId, knownLanguage) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/knownLanguages/${userId}/update`,
      knownLanguage
    );
  }
}
