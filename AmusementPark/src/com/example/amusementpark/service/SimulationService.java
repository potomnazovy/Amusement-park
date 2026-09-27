package com.example.amusementpark.service;

import com.example.amusementpark.model.attraction.Attraction;
import com.example.amusementpark.model.person.Employee;
import com.example.amusementpark.model.person.VipVisitor;
import com.example.amusementpark.report.DailyReport;
import com.example.amusementpark.model.person.Visitor;
import com.example.amusementpark.model.ticket.TicketType;
import com.example.amusementpark.model.ticket.Ticket;
import com.example.amusementpark.exception.InsufficientFundsException;
import com.example.amusementpark.exception.AccessDeniedException;

import java.util.List;
import java.util.Random;

public class SimulationService
{
  private final ParkService parkService;
  private final VisitorFactory visitorFactory;
  private final Employee employee;
  private final Random random = new Random();

  public SimulationService(ParkService parkService, Employee employee)
  {
    if (parkService == null)
    {
      throw new IllegalArgumentException("ParkService обязателен");
    }

    if (employee == null)
    {
      throw new IllegalArgumentException("Сотрудник должен быть указан");
    }

    this.parkService = parkService;
    this.visitorFactory = new VisitorFactory();
    this.employee = employee;
  }

  public DailyReport runDay(int visitorsCount, int openHour, int closeHour)
  {
    if (visitorsCount < 0)
    {
      throw new IllegalArgumentException("Количество посетителей не может быть отрицательным числом");
    }

    DailyReport report = new DailyReport();
    List< Visitor > visitors = visitorFactory.createRandom(visitorsCount);

    parkService.getPark().openAll();

    for (int i = 0; i < visitors.size(); ++i)
    {
      Visitor v = visitors.get(i);
      report.incrementVisitorsCame();

      List< Attraction > all = parkService.getPark().getAllAttractions();
      if (all.isEmpty())
      {
        continue;
      }

      Attraction choosen = all.get(random.nextInt(all.size()));

      try
      {
        TicketType type = chooseTicketType(v);
        Ticket ticket = parkService.buyTicket(v, type, employee, choosen);
        report.incrementTicketsSold();
        report.addRevenue(ticket.getPrice());
      }
      catch (InsufficientFundsException e)
      {
        report.incrementDeniedInCashier();
        continue;
      }

      int currentHour = randomHour(openHour, closeHour);
      try
      {
        parkService.ride(v, choosen, currentHour);
        report.incrementRidesCompleted();
      }
      catch (AccessDeniedException e)
      {
        report.incrementDeniedByAccess();
      }
    }

    parkService.getPark().closeAll();
    return report;
  }

  private TicketType chooseTicketType(Visitor visitor)
  {
    if (visitor.getAge() < 14)
    {
      return TicketType.CHILD;
    }

    if (visitor instanceof VipVisitor && random.nextDouble() < 0.5)
    {
      return TicketType.FAST_PASS;
    }

    return TicketType.ADULT;
  }

  private int randomHour(int openHour, int closeHour)
  {
    if (openHour < closeHour)
    {
      return openHour + random.nextInt(closeHour - openHour);
    }

    return openHour;
  }
}
