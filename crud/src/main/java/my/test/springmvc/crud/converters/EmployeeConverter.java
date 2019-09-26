package my.test.springmvc.crud.converters;

import my.test.springmvc.crud.entities.Department;
import my.test.springmvc.crud.entities.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("employeeConverter")
public class EmployeeConverter implements Converter<String, Employee> {
    @Override
    public Employee convert(String source) {
        if(source != null) {
            String [] vals = source.split("-");
            if(vals != null && vals.length == 4) {
                String lastName = vals[0];
                String email = vals[1];
                Integer gender = Integer.parseInt(vals[2]);
                Integer departmentId = Integer.parseInt(vals[3]);
                Department department = new Department();
                department.setId(departmentId);
                Employee employee = new Employee(null, lastName, email, gender, department);
                return employee;
            }
        }
        return null;
    }
}
