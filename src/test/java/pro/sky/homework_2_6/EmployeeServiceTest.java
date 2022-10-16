package pro.sky.homework_2_6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.homework_2_6.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_6.exeptions.EmployeeNotFoundException;
import pro.sky.homework_2_6.exeptions.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    Employee defaultEmployee = new Employee("Ivan", "Ivanov", 1, 1000);
    @BeforeEach
    public void addDefaultEmployee() {
        EmployeeService.getEmployeeBook().put("Ivan Ivanov", defaultEmployee);
    }

    @Test
    public void addNewEmployee() {
        Employee checkEmployee = new Employee("Petr", "Petrov", 1, 2000);
        assertEquals(checkEmployee,
                EmployeeService.addEmployee("Petr", "Petrov", 1, 2000));
    }

    @Test
    public void getExceptionTryingToAddEmployeeThatAlreadyExists() {
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                EmployeeService.addEmployee("Ivan", "Ivanov", 1, 1000));
    }

    @Test
    public void checkInputIsCorrect() {
        assertDoesNotThrow(() -> EmployeeService.checkInput("petr", "petrov"));
    }

    @Test
    public void checkInputThrowsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () ->
                EmployeeService.checkInput("petr", "pe1trov"));
        assertThrows(InvalidArgumentException.class, () ->
                EmployeeService.checkInput("pe1tr", "petrov"));
    }

    @Test
    public void createKeyIsCorrect() {
        assertEquals("Petr Petrov",
                EmployeeService.createKey("petr", "PetrOv"));
    }

    @Test
    public void removeEmployeeCorrectly() {
        assertEquals(defaultEmployee,
                EmployeeService.removeEmployee("ivan", "Ivanov"));
    }

    @Test
    public void getExceptionTryingToRemoveEmployeeThatDoesntExists() {
        assertThrows(EmployeeNotFoundException.class, () ->
                EmployeeService.removeEmployee("petr", "PetrOv"));
    }

    @Test
    public void findEmployeeCorrectly() {
        assertEquals(defaultEmployee,
                EmployeeService.findEmployee("ivan", "Ivanov"));
    }

    @Test
    public void getExceptionTryingToFindEmployeeThatDoesntExists() {
        assertThrows(EmployeeNotFoundException.class, () ->
                EmployeeService.findEmployee("petr", "PetrOv"));
    }
}
