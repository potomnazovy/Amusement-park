package com.example.amusementpark.report;

public class DailyReport
{
  private int visitorsCame;
  private int ticketsSold;
  private int deniedInCashier;
  private int ridesCompleted;
  private int deniedByAccess;
  private int totalRevenue;

  public int getVisitorsCame()
  {
    return this.visitorsCame;
  }

  public int getTicketsSold()
  {
    return this.ticketsSold;
  }

  public int getDeniedInCashier()
  {
    return this.deniedInCashier;
  }

  public int getRidesCompleted()
  {
    return this.ridesCompleted;
  }

  public int getDeniedByAccess()
  {
    return this.deniedByAccess;
  }

  public int getTotalRevenue()
  {
    return this.totalRevenue;
  }

  public int incrementVisitorsCame()
  {
    return this.visitorsCame++;
  }

  public int incrementTicketsSold()
  {
    return this.ticketsSold++;
  }

  public int incrementDeniedInCashier()
  {
    return this.deniedInCashier++;
  }

  public int incrementRidesCompleted()
  {
    return this.ridesCompleted++;
  }

  public int incrementDeniedByAccess()
  {
    return this.deniedByAccess;
  }

  public int incrementTotalRevenue()
  {
    return this.totalRevenue++;
  }
}
