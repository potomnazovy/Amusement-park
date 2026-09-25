package com.example.amusementpark.model.attraction;

public class RollerCoaster extends Attraction
{
  private int length; //метры
  private int maxHeight; //метры
  private int maxSpeed; //км/ч
  private int loopsCount; //шт

  public RollerCoaster(int price, int capacity, int extremity, int rideDuration, int minHeight, int minWeight,
    int openHour, int closeHour, int length, int maxHeight, int maxSpeed, int loopsCount)
  {
    super(price, capacity, extremity, rideDuration, minHeight, minWeight, openHour, closeHour);

    if (length <= 0)
    {
      throw new IllegalArgumentException("Протяженность трассы американских горок должна быть положительной");
    }

    if (maxHeight <= 0)
    {
      throw new IllegalArgumentException("Максимальная высота американских горок должна быть положительной");
    }

    if (maxSpeed <= 0)
    {
      throw new IllegalArgumentException("Максимальная скорость американских горок должна быть положительной");
    }

    if (loopsCount < 0)
    {
      throw new IllegalArgumentException("Количество \"мертвых петель\" не должно быть отрицательным");
    }

    this.length = length;
    this.maxHeight = maxHeight;
    this.maxSpeed = maxSpeed;
    this.loopsCount = loopsCount;
  }

  public int getLength()
  {
    return this.length;
  }

  public int getMaxHeight()
  {
    return this.maxHeight;
  }

  public int getMaxSpeed()
  {
    return this.maxSpeed;
  }

  public int getLoopsCount()
  {
    return this.loopsCount;
  }

  @Override
  public String getType()
  {
    return "Американские горки";
  }

  @Override
  public double getThroughput()
  {
    int totalCycle = rideDuration + 3;
    return capacity * (60.0 / totalCycle);
  }
}
