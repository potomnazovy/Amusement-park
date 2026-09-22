package com.example.amusementpark.service;

import com.example.amusementpark.model.person.Visitor;
import com.example.amusementpark.model.ticket.Ticket;
import com.example.amusementpark.model.ticket.TicketType;
import com.example.amusementpark.model.person.VipVisitor;
import com.example.amusementpark.exception.InsufficientFundsException;
import com.example.amusementpark.model.person.Employee;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TicketOffice
{
  private final int basePrice;
  private final List< Ticket > soldTickets = new ArrayList<>();
  private int ticketId = 1;
  private int cashInDrawer; // физические деньги в кассе (для возвратов)
  private int totalRevenue; // полная выручка за все время

  public TicketOffice(int basePrice, int cashInDrawer)
  {
    if (basePrice <= 0)
    {
      throw new IllegalArgumentException("Базовая цена билета должна быть положительной");
    }

    if (cashInDrawer < 0)
    {
      throw new IllegalArgumentException("Начальная сумма для сдачи должна быть положительной");
    }

    this.basePrice = basePrice;
    this.cashInDrawer = cashInDrawer;
    this.totalRevenue = 0;
  }

  public int getBasePrice()
  {
    return this.basePrice;
  }

  public List< Ticket > getSoldTickets()
  {
    return Collections.unmodifiableList(soldTickets);
  }

  public int getCashInDrawer()
  {
    return this.cashInDrawer;
  }

  public int getTotalRevenue()
  {
    return this.totalRevenue;
  }

  public int getSoldCount()
  {
    return soldTickets.size();
  }

  private String generateTicketId()
  {
    return "T-" + String.format("%05d", ticketId++);
  }

  private int calculatePrice(TicketType type, Visitor visitor)
  {
    double price = basePrice * type.getPriceCoefficient();
    if (visitor instanceof VipVisitor vip)
    {
      double remainder = (1 - vip.getDiscount());
      return (int) Math.round(remainder * price);
    }

    return (int) Math.round(price);
  }

  public Ticket sellTicket(Visitor visitor, TicketType type, Employee employee)
  {
    if (visitor == null)
    {
      throw new IllegalArgumentException("Посетитель обязан быть для покупки билета в кассе");
    }

    if (type == null)
    {
      throw new IllegalArgumentException("Тип билета должен быть указан");
    }

    if (employee == null)
    {
      throw new IllegalArgumentException("Продавец должен быть указан для покупки билета");
    }

    int ticketPrice = calculatePrice(type, visitor);

    if (visitor.getMoney() < ticketPrice)
    {
      throw new InsufficientFundsException(visitor.getName() + " не может купить билет за " + ticketPrice
        + " руб.: у него только " + visitor.getMoney() + " руб.");
    }

    visitor.spend(ticketPrice);
    this.cashInDrawer += ticketPrice;
    this.totalRevenue += ticketPrice;

    String id = generateTicketId();
    Ticket ticket = new Ticket(id, ticketPrice, visitor, employee, type);
    soldTickets.add(ticket);

    return ticket;
  }

  public void refundTicket(Ticket ticket)
  {
    if (ticket == null)
    {
      throw new IllegalArgumentException("Билет не может быть пустым");
    }

    if (!soldTickets.contains(ticket))
    {
      throw new IllegalArgumentException("Не обманывай меня, маленький халявщик)");
    }

    if (!ticket.isValid())
    {
      throw new IllegalStateException("Билет уже недействителен");
    }

    if (cashInDrawer < ticket.getPrice())
    {
      throw new IllegalStateException("В кассе недостаточно денег для возврата: нужно "
        + ticket.getPrice() + ", а в кассе " + cashInDrawer);
    }

    cashInDrawer -= ticket.getPrice();
    totalRevenue -= ticket.getPrice();
    ticket.getOwner().refund(ticket.getPrice());
    soldTickets.remove(ticket);
  }

  public void addCash(int cash)
  {
    if (cash <= 0)
    {
      throw new IllegalArgumentException("Не воруй бабки из кассы, и так уже нищие");
    }

    cashInDrawer += cash;
  }

  public int collectCash()
  {
    int collected = cashInDrawer;
    cashInDrawer = 0;
    return collected;
  }
}
