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
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        EmployeeService.addEmployee(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        return getEmployee(firstName + " " + lastName);
    }

    @RequestMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        EmployeeService.removeEmployee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return EmployeeService.findEmployee(firstName, lastName);
    }

    @RequestMapping("/printlist")
    public HashMap printList() {
        return (HashMap) employeeBook;
    }
}
