package pro.sky.homework_2_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService out;

    Employee MinSalaryEployee = new Employee("Ivan", "Ivanov", 1, 1000);
    Employee MaxSalaryEployee = new Employee("Petr", "Petrov", 1, 2000);

    Collection<Employee> employeeBookValues = new ArrayList<>(List.of(MinSalaryEployee, MaxSalaryEployee));

    @Test
    public void findsMinSalaryEmployeeDepartment() {
        when(EmployeeService.getValues()).thenReturn(employeeBookValues);
        assertEquals(Optional.of(MinSalaryEployee), DepartmentService.minSalaryEmployeeDepartment(1));
    }

}
