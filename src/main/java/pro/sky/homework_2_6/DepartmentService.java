package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Ищем сотрудников с минимальной ЗП в отделе
    protected static Optional<Employee> minSalaryEmployeeDepartment(int departmentID) {
        return Optional.ofNullable(EmployeeService.getValues().stream().
                filter(e -> e.getDepartmentID() == departmentID).
                min(Comparator.comparing(Employee::getEmployeeSalary)).orElseThrow(EmployeeNotFoundException::new));
    }

    //Ищем сотрудников с максимальной ЗП в отделе
    protected static Optional<Employee> maxSalaryEmployeeDepartment(int departmentID) {
        return Optional.ofNullable(EmployeeService.getValues().stream().
                filter(e -> e.getDepartmentID() == departmentID).
                max(Comparator.comparing(Employee::getEmployeeSalary)).orElseThrow(EmployeeNotFoundException::new));
    }

    //выводим данные отрудников отдела
    protected static List<Employee> allEmployeesDepartment(int departmentID) {
        return EmployeeService.getValues().stream().
                filter(e -> e.getDepartmentID() == departmentID).
                collect(Collectors.toList());
    }

    //список сотрудников, отсортированный по отделам
    protected static List<Employee> everyEmployeesDepartment() {
        return EmployeeService.getValues().stream().
                sorted(Comparator.comparing(Employee::getDepartmentID)).
                collect(Collectors.toList());
    }
}
