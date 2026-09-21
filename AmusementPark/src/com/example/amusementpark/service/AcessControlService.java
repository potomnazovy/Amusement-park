package com.example.amusementpark.service;

import com.example.amusementpark.exception.AccessDeniedException;
import com.example.amusementpark.model.attraction.Attraction;
import com.example.amusementpark.model.person.Person;

public class AcessControlService
{
  public void checkAccess(Person person, Attraction attraction)
  {
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
