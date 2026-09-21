package com.example.amusementpark.model.person;

public class Guard extends Employee
{
  private final String shift;
  private final int clearanceLevel;

  public Guard(String name, int age, int weight,
    int height, String employeeId, int salary, String shift, int clearanceLevel)
  {
    super(name, age, weight, height, employeeId, salary);

    if (shift == null || shift.isBlank())
    {
      throw new IllegalArgumentException("Смена не может быть пустой");
    }
    
    if (clearanceLevel <= 0)
    {
      throw new IllegalArgumentException("Уровень допуска должен быть положительным");
    }

    this.shift = shift;
    this.clearanceLevel = clearanceLevel;
  }

  public String getShift()
  {
    return shift;
  }

  public int getClearanceLevel()
  {
    return clearanceLevel;
  }

  @Override
  public String getRole()
  {
    return "Охранник";
  }
}
