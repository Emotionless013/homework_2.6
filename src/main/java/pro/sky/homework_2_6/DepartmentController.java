package pro.sky.homework_2_6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/min-salary")
    public Optional<Employee> minSalary(@RequestParam int departmentID) {
        return departmentService.minSalaryEmployeeDepartment(departmentID);
    }

    @RequestMapping("/max-salary")
    public Optional<Employee> maxSalary(@RequestParam int departmentID) {
        return departmentService.maxSalaryEmployeeDepartment(departmentID);
    }

    @RequestMapping("/all")
    public List<Employee> employeesDepartment(@RequestParam(defaultValue = "0") int departmentID) {
        if (departmentID != 0) {
            return departmentService.allEmployeesDepartment(departmentID);
        } else {
            return departmentService.everyEmployeesDepartment();
        }
    }
}
