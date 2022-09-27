package pro.sky.homework_2_6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static pro.sky.homework_2_6.EmployeeService.employeeBook;
import static pro.sky.homework_2_6.EmployeeService.getEmployee;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName,
                                @RequestParam int departmentID, @RequestParam double employeeSalary) {
        EmployeeService.addEmployee(firstName, lastName, departmentID, employeeSalary);
        Employee employee = new Employee(firstName, lastName, departmentID, employeeSalary);
        return getEmployee(firstName + " " + lastName);
    }

    @RequestMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        EmployeeService.removeEmployee(firstName, lastName);
        return firstName + " " + lastName + " удален";
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return EmployeeService.findEmployee(firstName, lastName);
    }

    @RequestMapping("/printlist")
    public HashMap<String, Employee> printList() {
        return (HashMap<String, Employee>) employeeBook;
    }
}
