package pro.sky.homework_2_6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;



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
            employeeService.checkInput(firstName, lastName);
            return employeeService.addEmployee(firstName, lastName, departmentID, employeeSalary);
    }

    @RequestMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        employeeService.checkInput(firstName, lastName);
        return employeeService.removeEmployee(firstName, lastName);
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        employeeService.checkInput(firstName, lastName);
        return employeeService.findEmployee(firstName, lastName);
    }

    @RequestMapping("/printlist")
    public HashMap<String, Employee> printList() {
        return (HashMap<String, Employee>) employeeService.getEmployeeBook();
    }
}
