package com.example.amusementpark.model.attraction;

public class FerrisWheel extends Attraction
{
  private int maxHeight;
  private int cabinsCount;
  private int cabinsCapacity;

  public FerrisWheel(int price, int capacity, int extremity, int rideDuration, int minHeight, int minWeight,
    int openHour, int closeHour, int maxHeight, int cabinsCount, int cabinsCapacity)
  {
    super(price, capacity, extremity, rideDuration, minHeight, minWeight, openHour, closeHour);

    if (maxHeight <= 0)
    {
      throw new IllegalArgumentException("Максимальная высота колеса обозрения должна быть положительной");
    }

    if (cabinsCount <= 0)
    {
      throw new IllegalArgumentException("Количество кабинок должно быть положительным числом");
    }

    if (cabinsCapacity <= 0)
    {
      throw new IllegalArgumentException(
        "В кабинку должны помещаться люди, поэтому количество людей в ней должно быть положительное число");
    }

    this.maxHeight = maxHeight;
    this.cabinsCount = cabinsCount;
    this.cabinsCapacity = cabinsCapacity;
  }

  public int getMaxHeight()
  {
    return this.maxHeight;
  }

  public int getCabinsCount()
  {
    return this.cabinsCount;
  }

  public int getCabinsCapacity()
  {
    return this.cabinsCapacity;
  }

  @Override
  public String getType()
  {
    return "Колесо обозрения";
  }

  @Override
  public double getThroughput()
  {
    return cabinsCount * cabinsCapacity * (60.0 / rideDuration);
  }
}
