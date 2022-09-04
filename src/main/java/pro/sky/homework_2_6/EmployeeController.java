package pro.sky.homework_2_6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework_2_6.exeptions.EmployeeStorageIsFullException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
            public String addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            addEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException e) {
            return "Список сотрудников уже заполнен.";
        } catch (RuntimeException w) {
            return "mistake";
        }
        finally {
            Employee employee = new Employee(firstName, lastName);
            return employee.toString();
        }


    }
}
