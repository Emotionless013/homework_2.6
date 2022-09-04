package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;
import pro.sky.homework_2_6.exeptions.EmployeeStorageIsFullException;

@Service
public class EmployeeService {

    private Employee[] employeeBook = new Employee[1];

    public Employee getEmployee(int i) {
        return employeeBook[i];
    }

    //добавляем сотрудника
    public void addEmployee(String firstName, String lastName) {
        try {
            if (findEmployee(firstName, lastName) == true) {
                throw new EmployeeAlreadyAddedException();
            }
        } catch (EmployeeNotFoundException z) {
            int j = -1;
            for (int i = 0; i < employeeBook.length; i++) {
                if (employeeBook[i] == null) {
                    j = i;
                    break;
                }
            }
            if (j != -1) {
                employeeBook[j] = new Employee(firstName, lastName);
            } else throw new EmployeeStorageIsFullException();
        }
    }

    //удаляем сотрудника
    public void removeEmployee(String firstName, String lastName) {
        int j = -1;
        for (int i = 0; i < employeeBook.length; i++) {
            if (employeeBook[i] != null && employeeBook[i].getFirstName() == firstName &&
                    employeeBook[i].getLastName() == lastName) {
                j = i;
            }
        }
        if (j != -1) {
        employeeBook[j] = null;
        } else throw new EmployeeNotFoundException();
    }

    //ищем сотрудника
    public boolean findEmployee(String firstName, String lastName) {
        Employee employeeCheck = new Employee(firstName, lastName);
        for (Employee employee : employeeBook) {
            if (employee != null && employeeCheck.equals(employee)) {
                return true;
            }
        }
        throw new EmployeeNotFoundException();
    }
}
