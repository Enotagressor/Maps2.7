package pro.sky.Maps27.employeeService;

import org.springframework.stereotype.Service;
import pro.sky.Maps27.empl.Employee;
import pro.sky.Maps27.exception.EmployeeAlreadyAddedException;
import pro.sky.Maps27.exception.EmployeeNotFoundException;
import pro.sky.Maps27.exception.EmployeeStoragesFullException;


import java.util.*;

@Service
public class EmployeeService {
    private final Map<Employee,Integer> employee;
    private static final Integer integer = 10;
    private static Integer counter = 1;
    private Integer id;

    public EmployeeService() {
        this.employee = new HashMap<>();

    }

    public String hello() {
        return "Добро пожаловать в базу данных сотрудников ООО <<РОГА И КОПЫТА>>";
    }


    public void addEmployee(Employee empl) {
        Integer index = null;
        if (employee.containsKey(empl)) {
            throw new EmployeeAlreadyAddedException();
        } else {
            index = employee.size();
        }
        if (index >= integer) {
            throw new EmployeeStoragesFullException();
        } else {id = counter++;
            employee.put(empl,id);
        }
    }

    public void removeEmployee(Employee empl) {
        if (!employee.containsKey(empl)) {
            throw new EmployeeNotFoundException();
        } else employee.remove(empl);
    }

    public String findEmployee(Employee empl) {
        if (employee.containsKey(empl)) {
            return empl.getLastName() + " " + empl.getFirstName();
        } else throw new EmployeeNotFoundException();
    }

    public Map<Employee, Integer> listEmployee() {
        return employee;
    }
}
