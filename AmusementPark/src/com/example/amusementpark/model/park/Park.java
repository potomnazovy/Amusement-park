package com.example.amusementpark.model.park;

import com.example.amusementpark.model.attraction.Attraction;
import com.example.amusementpark.model.person.Employee;
import com.example.amusementpark.model.person.Person;
import com.example.amusementpark.service.AccessControlService;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Park
{
  private final String name;
  private final List< Attraction > attractions = new ArrayList<>();
  private final List< Employee > employees = new ArrayList<>();

  public Park(String name)
  {
    if (name == null || name.isBlank())
    {
      throw new IllegalArgumentException("У парка должно быть название");
    }

    this.name = name;
  }

  public String getName()
  {
    return this.name;
  }

  public void addAttraction(Attraction attraction)
  {
    if (attraction == null)
    {
      throw new IllegalArgumentException("Аттракцион не может быть null");
    }

    this.attractions.add(attraction);
  }

  public void addEmployee(Employee employee)
  {
    if (employee == null)
    {
      throw new IllegalArgumentException("Сотрудник не может быть null");
    }

    this.employees.add(employee);
  }

  public List< Attraction > getAllAttractions()
  {
    return Collections.unmodifiableList(attractions);
  }

  public List< Employee > getAllEmployees()
  {
    return Collections.unmodifiableList(employees);
  }

  public int getAttractionsCount()
  {
    return this.attractions.size();
  }

  public int getEmployeesCount()
  {
    return this.employees.size();
  }

  public void openAll()
  {
    for (int i = 0; i < attractions.size(); ++i)
    {
      attractions.get(i).reopen();
    }
  }

  public void closeAll()
  {
    for (int i = 0; i < attractions.size(); ++i)
    {
      attractions.get(i).closeForManual();
    }
  }

  public void closeForMaintenance(Attraction attraction)
  {
    if (attraction == null)
    {
      throw new IllegalArgumentException("Аттракцион не может быть null");
    }

    if (!attractions.contains(attraction))
    {
      throw new IllegalArgumentException("Такого аттракциона нет в парке");
    }

    attraction.closeForMaintenance();
  }

  public List< Attraction > getOpenAttractions(int hour)
  {
    List< Attraction > list = new ArrayList<>();
    for (int i = 0; i < attractions.size(); ++i)
    {
      Attraction a = attractions.get(i);
      if (a.isOpenAt(hour))
      {
        list.add(a);
      }
    }

    return list;
  }

  public List< Attraction > getAvailableFor(Person person, int hour)
  {
    AccessControlService accessControl = new AccessControlService();
    List< Attraction > list = new ArrayList<>();

    for (int i = 0; i < attractions.size(); ++i)
    {
      Attraction a = attractions.get(i);
      if (accessControl.canAccess(person, a, hour))
      {
        list.add(a);
      }
    }

    return list;
  }

  public List< Attraction > searchMostExtremeAttraction()
  {
    List< Attraction > extremityAttractions = new ArrayList<>();
    if (attractions.isEmpty())
    {
      return extremityAttractions;
    }

    int mostExtremity = -1;

    for (int i = 0; i < attractions.size(); ++i)
    {
      Attraction a = attractions.get(i);
      int extrem = a.getExtremity();
      if (mostExtremity < extrem)
      {
        mostExtremity = extrem;
        extremityAttractions.clear();
        extremityAttractions.add(a);
      }
      else if (extrem == mostExtremity)
      {
        extremityAttractions.add(a);
      }
    }

    return extremityAttractions;
  }

  public double getAveragePrice()
  {
    if (attractions.isEmpty())
    {
      return 0.0;
    }

    int sum = 0;

    for (int i = 0; i < attractions.size(); ++i)
    {
      sum += attractions.get(i).getPrice();
    }

    return (double) sum / attractions.size();
  }
}
