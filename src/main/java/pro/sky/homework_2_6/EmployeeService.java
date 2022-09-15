package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    static Map<String, Employee> employeeBook = new HashMap<>(2);

    public static Employee getEmployee(String fullName) {
        return employeeBook.get(fullName);
    }

    //добавляем сотрудника
    public static void addEmployee(String firstName, String lastName, int employeeDepartment, double employeeSalary) {
        Employee employeeToAdd = new Employee(firstName, lastName, employeeDepartment, employeeSalary);
        try {
            findEmployee(firstName, lastName);
            throw new EmployeeAlreadyAddedException();
        } catch (EmployeeNotFoundException z) {
            employeeBook.put(employeeToAdd.getFullName(), employeeToAdd);
        }
    }

    //удаляем сотрудника
    public static void removeEmployee(String firstName, String lastName) {
        findEmployee(firstName, lastName);
        String employeeToRemove = firstName + " " + lastName;
        employeeBook.remove(employeeToRemove);
    }

    //ищем сотрудника
    public static Employee findEmployee(String firstName, String lastName) {
        String employeeCheck = firstName + " " + lastName;
        if (employeeBook.containsKey(employeeCheck)) {
            return employeeBook.get(employeeCheck);
        }
        throw new EmployeeNotFoundException();
    }
}


