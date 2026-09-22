package com.example.amusementpark.model.ticket;

import com.example.amusementpark.model.person.Visitor;
import com.example.amusementpark.model.person.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket
{
  private final String id;
  private final int price;
  private final Visitor owner;
  private final Employee seller;
  private final TicketType type;
  private final LocalDateTime purchasedAt;
  private final LocalDateTime validUntil;

  public Ticket(String id, int price, Visitor owner,
    Employee seller, TicketType type)
  {
    if (id == null || id.isBlank())
    {
      throw new IllegalArgumentException("ID билета не может быть пустым полем");
    }

    if (price < 0)
    {
      throw new IllegalArgumentException("Цена билета не должна быть отрицательной");
    }

    if (owner == null)
    {
      throw new IllegalArgumentException("Владелец билета обязательно должен быть указан");
    }

    if (seller == null)
    {
      throw new IllegalArgumentException("Продавец билета обязательно должен быть указан");
    }

    if (type == null)
    {
      throw new IllegalArgumentException("Тип билета не может быть пустым полем");
    }

    this.id = id;
    this.price = price;
    this.owner = owner;
    this.seller = seller;
    this.type = type;
    this.purchasedAt = LocalDateTime.now();
    this.validUntil = LocalDate.now().atTime(23, 59, 59);
  }

  public String getId()
  {
    return this.id;
  }

  public int getPrice()
  {
    return this.price;
  }

  public Visitor getOwner()
  {
    return this.owner;
  }

  public Employee getSeller()
  {
    return this.seller;
  }

  public TicketType getType()
  {
    return this.type;
  }

  public LocalDateTime getPurchasedAt()
  {
    return this.purchasedAt;
  }

  public LocalDateTime getValidUntil()
  {
    return this.validUntil;
  }

  public boolean isValid()
  {
    return LocalDateTime.now().isBefore(validUntil);
  }

  public boolean skipsQueue()
  {
    return type.isSkipQueue();
  }

  @Override
  public String toString()
  {
    return "Ticket[# " + id + " , купленный по цене " + price + " руб, имеет тип " + type.getDisplayName() +
      " и принадлежит посетителю " + owner.getName() + " ]";
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }

    if (o == null || this.getClass() != o.getClass())
    {
      return false;
    }

    Ticket ticket = (Ticket) o;
    return id.equals(ticket.id);
  }

  @Override
  public int hashCode()
  {
    return id.hashCode();
  }
}
