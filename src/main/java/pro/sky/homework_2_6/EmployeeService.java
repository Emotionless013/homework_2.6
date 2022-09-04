package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;
import pro.sky.homework_2_6.exeptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    static List<Employee> employeeBook = new ArrayList<>(2);

    public static Employee getEmployee(int i) {
        return employeeBook.get(i);
    }

    //добавляем сотрудника
    public static void addEmployee(String firstName, String lastName) {
        try {
            findEmployee(firstName, lastName);
            throw new EmployeeAlreadyAddedException();
        } catch (EmployeeNotFoundException z) {
            employeeBook.add(new Employee(firstName, lastName));
            return;
                }
    }

    //удаляем сотрудника
    public static void removeEmployee(String firstName, String lastName) {
        int i = findEmployee(firstName, lastName);
        employeeBook.set(i, null);
    }

    //ищем сотрудника
    public static int findEmployee(String firstName, String lastName) {
        Employee employeeCheck = new Employee(firstName, lastName);
        for (int i = 0; i < employeeBook.size(); i++) {
            if (employeeBook.get(i) != null && employeeCheck.equals(employeeBook.get(i))) {
                return i;
            }
        }
        throw new EmployeeNotFoundException();
    }
}
