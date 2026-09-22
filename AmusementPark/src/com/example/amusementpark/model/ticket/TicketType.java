package com.example.amusementpark.model.ticket;

public enum TicketType
{
  CHILD(0.5, false),
  ADULT(1.0, false),
  FAST_PASS(2.0, true);

  private double priceСoefficient;
  private boolean skipQueue;

  TicketType(double priceСoefficient, boolean skipQueue)
  {
    this.priceСoefficient = priceСoefficient;
    this.skipQueue = skipQueue;
  }

  public double getPriceCoefficient()
  {
    return this.priceСoefficient;
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
