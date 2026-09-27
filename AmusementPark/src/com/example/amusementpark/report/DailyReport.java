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

  public void incrementVisitorsCame()
  {
    this.visitorsCame++;
  }

  public void incrementTicketsSold()
  {
    this.ticketsSold++;
  }

  public void incrementDeniedInCashier()
  {
    this.deniedInCashier++;
  }

  public void incrementRidesCompleted()
  {
    this.ridesCompleted++;
  }

  public void incrementDeniedByAccess()
  {
    this.deniedByAccess++;
  }

  public void addRevenue(int amount)
  {
    this.totalRevenue += amount;
  }
}
