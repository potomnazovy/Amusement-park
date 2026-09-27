package com.example.amusementpark.service;

import com.example.amusementpark.model.attraction.Attraction;
import com.example.amusementpark.model.park.Park;
import com.example.amusementpark.model.person.Employee;
import com.example.amusementpark.model.person.Visitor;
import com.example.amusementpark.model.ticket.TicketType;
import com.example.amusementpark.model.ticket.Ticket;

public class ParkService
{
  private final Park park;
  private final TicketOffice ticketOffice;
  private final AccessControlService accessControl;

  public ParkService(Park park, TicketOffice ticketOffice)
  {
    if (park == null)
    {
      throw new IllegalArgumentException("Парк должен быть указан");
    }

    if (ticketOffice == null)
    {
      throw new IllegalArgumentException("Касса должна быть указана");
    }

    this.park = park;
    this.ticketOffice = ticketOffice;
    this.accessControl = new AccessControlService();
  }

  public Ticket buyTicket(Visitor visitor, TicketType type, Employee employee)
  {
    return ticketOffice.sellTicket(visitor, type, employee);
  }

  public void ride(Visitor visitor, Attraction attraction, int hour)
  {
    accessControl.checkAccess(visitor, attraction, hour);
  }

  public boolean canRide(Visitor visitor, Attraction attraction, int hour)
  {
    return accessControl.canAccess(visitor, attraction, hour);
  }

  public Park getPark()
  {
    return this.park;
  }

  public TicketOffice getTicketOffice()
  {
    return this.ticketOffice;
  }
}
