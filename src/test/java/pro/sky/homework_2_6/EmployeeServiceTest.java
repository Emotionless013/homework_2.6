package pro.sky.homework_2_6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;
import pro.sky.homework_2_6.exeptions.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    public final EmployeeService employeeService;

    Employee defaultEmployee = new Employee("Ivan", "Ivanov", 1, 1000);

    public EmployeeServiceTest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @BeforeEach
    public void addDefaultEmployee() {
        employeeService.getEmployeeBook().put("Ivan Ivanov", defaultEmployee);
    }

    @Test
    public void addNewEmployee() {
        Employee checkEmployee = new Employee("Petr", "Petrov", 1, 2000);
        assertEquals(checkEmployee,
                employeeService.addEmployee("Petr", "Petrov", 1, 2000));
    }

    @Test
    public void getExceptionTryingToAddEmployeeThatAlreadyExists() {
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee("Ivan", "Ivanov", 1, 1000));
    }

    @Test
    public void checkInputIsCorrect() {
        assertDoesNotThrow(() -> employeeService.checkInput("petr", "petrov"));
    }

    @Test
    public void checkInputThrowsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () ->
                employeeService.checkInput("petr", "pe1trov"));
        assertThrows(InvalidArgumentException.class, () ->
                employeeService.checkInput("pe1tr", "petrov"));
    }

    @Test
    public void createKeyIsCorrect() {
        assertEquals("Petr Petrov",
                employeeService.createKey("petr", "PetrOv"));
    }

    @Test
    public void removeEmployeeCorrectly() {
        assertEquals(defaultEmployee,
                employeeService.removeEmployee("ivan", "Ivanov"));
    }

    @Test
    public void getExceptionTryingToRemoveEmployeeThatDoesntExists() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("petr", "PetrOv"));
    }

    @Test
    public void findEmployeeCorrectly() {
        assertEquals(defaultEmployee,
                employeeService.findEmployee("ivan", "Ivanov"));
    }

    @Test
    public void getExceptionTryingToFindEmployeeThatDoesntExists() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("petr", "PetrOv"));
    }
}
