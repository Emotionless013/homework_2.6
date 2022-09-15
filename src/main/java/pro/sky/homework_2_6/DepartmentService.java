package pro.sky.homework_2_6;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends EmployeeService {
    //Ищем сотрудников с минимальной ЗП в отделе
    protected static List<Employee> minSalaryEmployeeDepartment(int departmentID) {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        double salaryMin = workList.stream().filter(e -> e.getDepartmentID() == departmentID).
                map(s -> s.getEmployeeSalary()).min(Comparator.naturalOrder()).get();
        List<Employee> minSalaryEmployee = workList.stream().
                filter(e -> e.getDepartmentID() == departmentID).
                filter(employee -> employee.getEmployeeSalary() == salaryMin).
                collect(Collectors.toList());
        return minSalaryEmployee;
    }

    //Ищем сотрудников с максимальной ЗП в отделе
    protected static List<Employee> maxSalaryEmployeeDepartment(int departmentID) {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        double salaryMax = workList.stream().filter(e -> e.getDepartmentID() == departmentID).
                map(s -> s.getEmployeeSalary()).max(Comparator.naturalOrder()).get();
        List<Employee> maxSalaryEmployee = workList.stream().
                filter(e -> e.getDepartmentID() == departmentID).
                filter(employee -> employee.getEmployeeSalary() == salaryMax).
                collect(Collectors.toList());
        return maxSalaryEmployee;
    }

    //выводим данные отрудников отдела
    protected static List<Employee> allEmployeesDepartment(int departmentID) {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        List<Employee> allEmployeesDepartment = workList.stream().
                filter(e -> e.getDepartmentID() == departmentID).
                collect(Collectors.toList());
        return allEmployeesDepartment;
    }
    //список сотрудников, отсортированный по отделам
    protected static List<Employee> everyEmployeesDepartment() {
        List<Employee> workList = new ArrayList<>(employeeBook.values());
        List<Employee> everyEmployeesDepartment = workList.stream().
                sorted(Comparator.comparing(e -> e.getDepartmentID())).
                collect(Collectors.toList());
        return everyEmployeesDepartment;
    }
}
