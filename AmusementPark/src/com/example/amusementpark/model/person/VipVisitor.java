package com.example.amusementpark.model.person;

public class VipVisitor extends Visitor
{
  private String vipCardNumber;
  private double discount;

  public VipVisitor(String name,
    int age, int weight, int height, int money, String vipCardNumber, double discount)
  {
    super(name, age, weight, height, money);

    if (vipCardNumber == null || vipCardNumber.isBlank())
    {
      throw new IllegalArgumentException("Номер карты не может быть пустой");
    }

    if (discount < 0 || discount > 1)
    {
      throw new IllegalArgumentException("Скидка не может быть меньше нуля, а также не должна превышать 100%");
    }

    this.vipCardNumber = vipCardNumber;
    this.discount = discount;
  }

  public String getVipCardNumber()
  {
    return this.vipCardNumber;
  }

  public double getDiscount()
  {
    return this.discount;
  }

  @Override
  public String getRole()
  {
    return "VIP-клиент";
  }
}
