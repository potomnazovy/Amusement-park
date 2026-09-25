package com.example.amusementpark.service;

import com.example.amusementpark.exception.AccessDeniedException;
import com.example.amusementpark.model.attraction.Attraction;
import com.example.amusementpark.model.person.Person;

public class AccessControlService
{
  public void checkAccess(Person person, Attraction attraction, int hour)
  {
    if (person == null)
    {
      throw new IllegalArgumentException("Посетитель должен быть указан");
    }

    if (attraction == null)
    {
      throw new IllegalArgumentException("Аттракцион должен быть указан");
    }

    if (!attraction.isOpenAt(hour))
    {
      throw new AccessDeniedException("Аттракцион \"" + attraction.getType()
        + "\" закрыт в " + hour + ":00");
    }

    if (person.getHeight() < attraction.getMinHeight())
    {
      throw new AccessDeniedException(person.getName() + " Не проходит по росту, так как его рост составляет " +
        person.getHeight() + ", а нужно хотя бы " + attraction.getMinHeight());
    }

    if (person.getWeight() < attraction.getMinWeight())
    {
      throw new AccessDeniedException(person.getName() + "Не проходит по весу, так как его вес составляет " +
        person.getWeight() + ", а нужно хотя бы " + attraction.getMinWeight());
    }
  }
}
