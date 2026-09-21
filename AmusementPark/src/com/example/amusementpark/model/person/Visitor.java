package com.example.amusementpark.model.person;

public abstract class Visitor extends Person
{
  protected int money;

  protected Visitor(String name, int age, int weight, int height, int money)
  {
    super(name, age, weight, height);
    if (money < 0)
    {
      throw new IllegalArgumentException("Деньги не могут быть отрицательными");
    }

    this.money = money;
  }

  public int getMoney()
  {
    return money;
  }

  public void spend(int amount)
  {
    if (amount < 0)
    {
      throw new IllegalArgumentException("Сумма не может быть отрицательной");
    }

    if (amount > money)
    {
      throw new IllegalStateException("Недостаточно денег");
    }

    this.money -= amount;
  }
}
