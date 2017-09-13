package com.williamhill.protobuf;

public class EmployeeJson {

    String firstname;
    String lastname;
    int id;
    double salary;

    public EmployeeJson() {
    }

    public EmployeeJson(final String firstname, final String lastname, final int id, final double salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeJson{" + "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", id=" + id + ", salary=" + salary + '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }
}
