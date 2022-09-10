package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;
import pro.sky.homework_2_6.exeptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    static Map<String, Employee> employeeBook = new HashMap<>(2);

    public static Employee getEmployee(String i) {
        return employeeBook.get(i);
    }

    //добавляем сотрудника
    public static void addEmployee(String firstName, String lastName) {
        Employee employeeToAdd = new Employee(firstName, lastName);
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
        Employee employeeToRemove = new Employee(firstName, lastName);
        employeeBook.remove(employeeToRemove.getFullName());
    }

    //ищем сотрудника
    public static Employee findEmployee(String firstName, String lastName) {
        Employee employeeCheck = new Employee(firstName, lastName);
        if (employeeBook.containsKey(employeeCheck.getFullName())) {
                return employeeBook.get(employeeCheck.getFullName());
            }
        throw new EmployeeNotFoundException();
    }
}
