package root.database.service;

import root.database.dao.DaoManager;
import root.database.model.Employee;

import java.util.List;

public class EmployeeService {
    private DaoManager<Employee> daoManager;

    public EmployeeService() {
        daoManager = new DaoManager<>(Employee.class);
    }

    public Employee findByFullName(String fullName) {
        for (var employee : findAll()) {
            if (employee.getFullName().equals(fullName)) {
                return employee;
            }
        }
        return null;
    }

    public Employee findById(int id) {
        return daoManager.findById(id);
    }

    public void save(Employee employee) {
        daoManager.save(employee);
    }

    public void update(Employee employee) {
        daoManager.update(employee);
    }

    public void delete(Employee employee) {
        daoManager.delete(employee);
    }

    public List<Employee> findAll() {
        return daoManager.findAll();
    }

}
