package com.example.amusementpark.model.ticket;

public enum TicketType
{
  CHILD(0.5, false),
  ADULT(1.0, false),
  FAST_PASS(2.0, true);

  private final double priceCoefficient;
  private final boolean skipQueue;

  TicketType(double priceCoefficient, boolean skipQueue)
  {
    this.priceCoefficient = priceCoefficient;
    this.skipQueue = skipQueue;
  }

  public double getPriceCoefficient()
  {
    return this.priceCoefficient;
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
      case FAST_PASS -> "FAST_PASS";
    };
  }
}
