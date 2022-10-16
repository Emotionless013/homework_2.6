package pro.sky.homework_2_6;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;
import pro.sky.homework_2_6.exeptions.InvalidArgumentException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public static Map<String, Employee> employeeBook = new HashMap<>(2);


    public static Map<String, Employee> getEmployeeBook() {
        return employeeBook;
    }

    public static Collection<Employee> getValues() {
        return getEmployeeBook().values();
    }

    private static Employee getEmployee(String fullName) {
        return employeeBook.get(fullName);
    }

    //добавляем сотрудника
    public static Employee addEmployee(String firstName, String lastName, int employeeDepartment, double employeeSalary) {
        Employee employeeToAdd = new Employee(firstName, lastName, employeeDepartment, employeeSalary);
        try {
            findEmployee(firstName, lastName);
            throw new EmployeeAlreadyAddedException();
        } catch (EmployeeNotFoundException z) {
            employeeBook.put(employeeToAdd.getFullName(), employeeToAdd);
            return employeeToAdd;
        }
    }

    //удаляем сотрудника
    public static Employee removeEmployee(String firstName, String lastName) {
        findEmployee(firstName, lastName);
        String employeeToRemove = createKey(firstName, lastName);
        return employeeBook.remove(employeeToRemove);
    }

    //ищем сотрудника
    public static Employee findEmployee(String firstName, String lastName) {
        String employeeCheck = createKey(firstName, lastName);
        if (employeeBook.containsKey(employeeCheck)) {
            return employeeBook.get(employeeCheck);
        }
        throw new EmployeeNotFoundException();
    }

    public static void checkInput(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new InvalidArgumentException();
        }
    }
    public static String createKey(String firstName, String lastName) {
        return StringUtils.capitalize(firstName.toLowerCase()) + " " +
                StringUtils.capitalize(lastName.toLowerCase());
    }
}


