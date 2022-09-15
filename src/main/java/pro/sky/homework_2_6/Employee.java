package pro.sky.homework_2_6;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private int departmentID;
    private double employeeSalary;

    public Employee(String firstName, String lastName, int departmentID, double employeeSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.employeeSalary = employeeSalary;
        this.fullName = firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFullName(), employee.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName());
    }

    @Override
    public String toString() {
        return "Employee: " + this.fullName;
    }
}
