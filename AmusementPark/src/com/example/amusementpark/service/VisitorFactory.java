package com.example.amusementpark.service;

import java.util.Random;

import com.example.amusementpark.model.person.RegularVisitor;
import com.example.amusementpark.model.person.VipVisitor;
import com.example.amusementpark.model.person.Visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorFactory
{
  private final String[] NAMES =
  {
    "Вася", "Петя", "Аня", "Маша", "Коля", "Оля", "Дима", "Лена",
    "Игорь", "Света", "Женя", "Катя", "Миша", "Таня", "Серёжа", "Наташа",
    "Артём", "Юля", "Кирилл", "Вика", "Гоша", "Даша", "Рома", "Лиза"
  };

  private final Random random;

  public VisitorFactory()
  {
    this.random = new Random();
  }

  public VisitorFactory(long seed)
  {
    this.random = new Random();
  }

  public Visitor createRandom()
  {
    String name = randomName();
    int age = 5 + random.nextInt(70);
    int height = 110 + random.nextInt(101);
    int weight = 20 + random.nextInt(131);
    int money = 500 + random.nextInt(4501);

    if (random.nextInt(100) < 20)
    {
      String card = "VIP-" + (100 + random.nextInt(900));
      double discount = 0.1 + random.nextDouble() * 0.2;
      return new VipVisitor(name, age, weight, height, money, card, discount);
    }

    return new RegularVisitor(name, age, weight, height, money, false);
  }

  public List< Visitor > createRandom(int count)
  {
    if (count < 0)
    {
      throw new IllegalArgumentException("Количество посетителей не может быть равно 0");
    }

    List< Visitor > list = new ArrayList<>();
    for (int i = 0; i < count; ++i)
    {
      list.add(createRandom());
    }

    return list;
  }

  public String randomName()
  {
    return NAMES[random.nextInt(NAMES.length)];
  }
}
