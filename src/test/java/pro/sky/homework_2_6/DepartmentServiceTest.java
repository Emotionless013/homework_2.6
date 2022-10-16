package pro.sky.homework_2_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService out;

    Employee MinSalaryEmployee = new Employee("Ivan", "Ivanov", 1, 1000);
    Employee MaxSalaryEmployee = new Employee("Petr", "Petrov", 1, 2000);
    Employee OtherDepartmentEmployee = new Employee("Semyon", "Semyonov", 2, 2000);

    Collection<Employee> employeeBookValues = new ArrayList<>(List.of(MinSalaryEmployee, OtherDepartmentEmployee,
            MaxSalaryEmployee));

    Collection<Employee> employeeBookValuesSorted = employeeBookValues.stream().
            sorted(Comparator.comparing(Employee::getDepartmentID)).
            collect(Collectors.toList());

    @Test
    public void findsMinSalaryEmployeeDepartment() {
        when(employeeService.getValues()).thenReturn(employeeBookValues);
        assertEquals(Optional.of(MinSalaryEmployee), out.minSalaryEmployeeDepartment(1));
    }

    @Test
    public void findsMaxSalaryEmployeeDepartment() {
        when(employeeService.getValues()).thenReturn(employeeBookValues);
        assertEquals(Optional.of(MaxSalaryEmployee), out.maxSalaryEmployeeDepartment(1));
    }

    @Test
    public void throwExceptionTryingToFindMinSalaryEmployeeWithIncorrectDepartment() {
        when(employeeService.getValues()).thenReturn(employeeBookValues);
        assertThrows(EmployeeNotFoundException.class, () ->
                out.minSalaryEmployeeDepartment(3));
    }
    @Test
    public void throwExceptionTryingToFindMaxSalaryEmployeeWithIncorrectDepartment() {
        when(employeeService.getValues()).thenReturn(employeeBookValues);
        assertThrows(EmployeeNotFoundException.class, () ->
                out.maxSalaryEmployeeDepartment(3));
    }
    @Test
    public void findsEmployeeByDepartment() {
        when(employeeService.getValues()).thenReturn(employeeBookValues);
        assertIterableEquals(List.of(MinSalaryEmployee, MaxSalaryEmployee), out.allEmployeesDepartment(1));
    }
    @Test
    public void returnsEmployeesSortedByDepartment() {
        when(employeeService.getValues()).thenReturn(employeeBookValues);
        assertIterableEquals(employeeBookValuesSorted, out.everyEmployeesDepartment());
    }

}
