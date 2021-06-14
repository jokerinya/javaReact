import axios from 'axios';

export default class GraduatedSchoolService {
  getByJobSeekerId(userId) {
    return axios.get(
      `${process.env.REACT_APP_BACKEND_API_URL}/graduatedSchools/${userId}/getByJobSeekerIdOrderByGraduationDate`
    );
  }

  delete(userId, graduatedSchool){
    return axios.delete(
        `${process.env.REACT_APP_BACKEND_API_URL}/graduatedSchools/${userId}/delete`, {data:graduatedSchool});
  }
  /**
   * add method sample object requirement
{
  "department": {
    "departmentName": "Mechanical Engineering"
    },
  "graduationDate": "2020-06-01",
  "school": {
    "schoolName": "Ankara University"
  },
  "startDate": "2015-06-01"
}
   */
  add(userId, graduatedSchool){
    return axios.post(
        `${process.env.REACT_APP_BACKEND_API_URL}/graduatedSchools/${userId}/add`, graduatedSchool)
  }

  /**
   * Demo object for update
{
  "department": {
    "departmentName": "biology"
  },
  "graduatedSchoolId": 13,
  "graduationDate": "2020-06-14",
  "school": {
    "schoolName": "galatasaray university"
  },
  "startDate": "2014-06-01"
}
   */
  update(userId, graduatedSchool){
    return axios.put(`${process.env.REACT_APP_BACKEND_API_URL}/graduatedSchools/${userId}/update`, graduatedSchool)
  }
}
