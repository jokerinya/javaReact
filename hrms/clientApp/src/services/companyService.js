import axios from 'axios';

export default class CompanyService {
  getAll() {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/companies/getall`
    );
  }

  getByCompanyId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/companies/${userId}`
    );
  }

  /**
   * Add Demo Obj
{
  "companyName": "A Brand New Company",
  "companyPhone": "+1 987 765 44 33",
  "companyWebsite": "www.brandofnew.org",
  "email": "hr@brandofnew.org",
  "password": "veryImportant",
  "userId": 0
}
   */

  add(company) {
    return axios.post(
      `${process.env.REACT_APP_BACKEND_API_URL}/companies/add`,
      company
    );
  }

  /**
   * Update Fields demo obj
{
  "companyName": "Excellent Company",
  "companyPhone": "+90 123 122 11 22",
  "companyWebsite": "www.super.org"
}
   */

  updateFields(userId, company) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/companies/${userId}/updateFields`,
      company
    );
  }

  updatePassword(userId, newPassword) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/companies/${userId}/updatePassword`,
      newPassword
    );
  }

  updateEmail(userId, newEmail) {
    return axios.put(
      `${process.env.REACT_APP_BACKEND_API_URL}/companies/${userId}/updateEmail`,
      newEmail
    );
  }
}
