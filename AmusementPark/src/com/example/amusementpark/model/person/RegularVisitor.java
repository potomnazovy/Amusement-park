package com.example.amusementpark.model.person;

public class RegularVisitor extends Visitor
{
  private boolean sesonPass;

  public RegularVisitor(String name, int age, int weight, int height, int money, boolean sesonPass)
  {
    super(name, age, weight, height, money);
    this.sesonPass = sesonPass;
  }

  public boolean hasSesonPass()
  {
    return this.sesonPass;
  }

  @Override
  public String getRole()
  {
    return "Обычный посетитель";
  }
}
