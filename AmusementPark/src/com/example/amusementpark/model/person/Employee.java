package com.example.amusementpark.model.person;

public abstract class Employee extends Person
{
  protected final String employeeId;
  protected int salary;

  protected Employee(String name, int age,
    int weight, int height, String employeeId, int salary)
  {
    super(name, age, weight, height);

    if (employeeId == null || employeeId.isBlank())
    {
      throw new IllegalArgumentException("Id работника не может быть пустым");
    }

    if (salary <= 0)
    {
      throw new IllegalArgumentException("Запрещено использовать черно-рабочих, хватит над ними издеваться!");
    }

    this.employeeId = employeeId;
    this.salary = salary;
  }

  public String getEmployeeId()
  {
    return this.employeeId;
  }

  public int getSalary()
  {
    return this.salary;
  }
}
