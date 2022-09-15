package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends EmployeeService {
    //Ищем сотрудников с минимальной ЗП в отделе
    protected static List<Employee> minSalaryEmployeeDepartment(int departmentID) {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        double salaryMin = workList.stream().filter(e -> e.getDepartmentID() == departmentID).
                map(s -> s.getEmployeeSalary()).min(Comparator.naturalOrder()).
                orElseThrow(NoSuchElementException::new);
        return workList.stream().
                filter(e -> e.getDepartmentID() == departmentID).
                filter(employee -> employee.getEmployeeSalary() == salaryMin).
                collect(Collectors.toList());
    }

    //Ищем сотрудников с максимальной ЗП в отделе
    protected static List<Employee> maxSalaryEmployeeDepartment(int departmentID) {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        double salaryMax = workList.stream().filter(e -> e.getDepartmentID() == departmentID).
                map(s -> s.getEmployeeSalary()).max(Comparator.naturalOrder()).
                orElseThrow(NoSuchElementException::new);
        return workList.stream().
                filter(e -> e.getDepartmentID() == departmentID).
                filter(employee -> employee.getEmployeeSalary() == salaryMax).
                collect(Collectors.toList());
    }

    //выводим данные отрудников отдела
    protected static List<Employee> allEmployeesDepartment(int departmentID) {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        return workList.stream().
                filter(e -> e.getDepartmentID() == departmentID).
                collect(Collectors.toList());
    }
    //список сотрудников, отсортированный по отделам
    protected static List<Employee> everyEmployeesDepartment() {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        return workList.stream().
                sorted(Comparator.comparing(e -> e.getDepartmentID())).
                collect(Collectors.toList());
    }
}
