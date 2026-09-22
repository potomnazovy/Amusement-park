package com.example.amusementpark.model.ticket;

public enum TicketType
{
  CHILD(0.5, false),
  ADULT(1.0, false),
  FAST_PASS(2.0, true);

  private double price;
  private boolean skipQueue;

  TicketType(double price, boolean skipQueue)
  {
    this.price = price;
    this.skipQueue = skipQueue;
  }

  public double getPrice()
  {
    return this.price;
  }

  public boolean isSkipQueue()
  {
    return this.skipQueue;
  }

  public String getDisplayName()
  {
    return switch (this)
    {
      case CHILD -> "Детский";
      case ADULT -> "Взрослый";
      case FAST_PASS -> "Без очереди";
    };
  }
}
