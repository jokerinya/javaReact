package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.Department;

public interface DepartmentService {
    Department getByDepartmentNameIfNotCreate(String departmentName);
}
