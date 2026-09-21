package com.example.amusementpark.model.person;

public abstract class Person
{
  protected String name;
  protected int age;
  protected int weight;
  protected int height;

  protected Person(String name, int age, int weight, int height)
  {
    if (name == null || name.isBlank())
    {
      throw new IllegalArgumentException("Имя не может быть пустым");
    }

    if (age < 0)
    {
      throw new IllegalArgumentException("Возраст не может быть отрицательным");
    }

    if (weight <= 0)
    {
      throw new IllegalArgumentException("Вес должен быть положительным");
    }

    if (height <= 0)
    {
      throw new IllegalArgumentException("Рост должен быть положительным");
    }

    this.name = name;
    this.age = age;
    this.weight = weight;
    this.height = height;
  }

  public String getName()
  {
    return this.name;
  }

  public int getAge()
  {
    return this.age;
  }

  public int getWeight()
  {
    return this.weight;
  }

  public int getHeight()
  {
    return this.height;
  }

  public abstract String getRole();
}
