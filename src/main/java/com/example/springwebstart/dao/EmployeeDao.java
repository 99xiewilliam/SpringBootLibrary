package com.example.springwebstart.dao;

import com.example.springwebstart.pojo.Department;
import com.example.springwebstart.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();

        employees.put(101, new Employee(101, "AA", "A123@qq.com", 0, new Department(101, "教学部")));
        employees.put(102, new Employee(102, "BB", "B123@qq.com", 1, new Department(102, "市场部")));
        employees.put(103, new Employee(103, "CC", "C123@qq.com", 1, new Department(103, "教研部")));
        employees.put(104, new Employee(104, "DD", "D123@qq.com", 0, new Department(104, "运营部")));
        employees.put(105, new Employee(105, "EE", "E123@qq.com", 1, new Department(105, "后勤部")));
    }

    private static Integer initId = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
