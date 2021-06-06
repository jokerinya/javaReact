package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.DepartmentService;
import kodlamaio.hrms.dataAccess.abstracts.DepartmentDao;
import kodlamaio.hrms.entities.concretes.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentManager implements DepartmentService {
    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentManager(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getByDepartmentNameIfNotCreate(String departmentName) {
        Department department = this.departmentDao.getByDepartmentName(departmentName);
        if (department != null){
            return department;
        }
        // A new department
        department = new Department();
        department.setDepartmentName(departmentName);
        this.departmentDao.save(department);
        // Renew the query
        return this.departmentDao.getByDepartmentName(departmentName);
    }
}
