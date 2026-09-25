package com.example.amusementpark.model.attraction;

public abstract class Attraction
{
  protected int price; //цены примем в рублях
  protected int capacity;
  protected int extremity; //экстремальность аттракциона примем в пределах от 1 до 10
  protected int rideDuration; //продолжительность поездки на аттракционе примем в минутах
  protected int minHeight; //минимальный рост для посещения аттракциона в см
  protected int minWeight; //минимальный вес для посещения аттракциона в кг

  protected int openHour;
  protected int closeHour;

  protected AttractionStatus status;

  protected Attraction(int price, int capacity, int extremity, int rideDuration,
    int minHeight, int minWeight, int openHour, int closeHour)
  {
    if (price <= 0)
    {
      throw new IllegalArgumentException("Цена должна быть положительной");
    }

    if (capacity <= 0)
    {
      throw new IllegalArgumentException("Вместительность аттракциона должна быть положительной");
    }

    if (extremity <= 0 || extremity > 10)
    {
      throw new IllegalArgumentException("Экстремальность аттракциона должна быть от 1 до 10");
    }

    if (rideDuration <= 0)
    {
      throw new IllegalArgumentException("Продолжительность поездки на аттракционе должна быть положительной");
    }

    if (minHeight <= 0)
    {
      throw new IllegalArgumentException("Минимальный рост должен быть положительным");
    }

    if (minWeight <= 0)
    {
      throw new IllegalArgumentException("Минимальный вес должен быть положительным");
    }

    if (openHour < 0 || openHour > 23)
    {
      throw new IllegalArgumentException("Время открытия должно быть от 00:00 до 23:00");
    }

    if (closeHour < 0 || closeHour > 23)
    {
      throw new IllegalArgumentException("Время закрытия должно быть от 00:00 до 23:00");
    }

    if (openHour == closeHour)
    {
      throw new IllegalArgumentException("Часы открытия и закрытия не могу совпадать");
    }

    this.price = price;
    this.capacity = capacity;
    this.extremity = extremity;
    this.rideDuration = rideDuration;
    this.minHeight = minHeight;
    this.minWeight = minWeight;
    this.openHour = openHour;
    this.closeHour = closeHour;
    this.status = AttractionStatus.OPEN;
  }

  public int getPrice()
  {
    return this.price;
  }

  public int getCapacity()
  {
    return this.capacity;
  }

  public int getExtremity()
  {
    return this.extremity;
  }

  public int getRideDuration()
  {
    return this.rideDuration;
  }

  public int getMinHeight()
  {
    return this.minHeight;
  }

  public int getMinWeight()
  {
    return this.minWeight;
  }

  public int getOpenHour()
  {
    return this.openHour;
  }

  public int getCloseHour()
  {
    return this.closeHour;
  }

  public AttractionStatus getStatus()
  {
    return this.status;
  }

  public void closeForManual()
  {
    this.status = AttractionStatus.CLOSED_MANUAL;
  }

  public void closeForWeather()
  {
    this.status = AttractionStatus.CLOSED_WEATHER;
  }

  public void closeForMaintenance()
  {
    this.status = AttractionStatus.CLOSED_MAINTENANCE;
  }

  public void reopen()
  {
    this.status = AttractionStatus.OPEN;
  }

  public boolean isBlocked()
  {
    return this.status == AttractionStatus.CLOSED_MANUAL
      || this.status == AttractionStatus.CLOSED_MAINTENANCE
      || this.status == AttractionStatus.CLOSED_WEATHER;
  }

  public boolean isOpenAt(int hour)
  {
    if (isBlocked())
    {
      return false;
    }

    if (openHour < closeHour)
    {
      return openHour <= hour && hour < closeHour;
    }
    else
    {
      return openHour <= hour || hour < closeHour;
    }
  }

  public AttractionStatus getStatusAt(int hour)
  {
    if (isBlocked())
    {
      return this.status;
    }

    if (!isOpenAt(hour))
    {
      return AttractionStatus.CLOSED_SCHEDULE;
    }

    return AttractionStatus.OPEN;
  }

  public abstract String getType();
  public abstract double getThroughput();
}
