package com.example.amusementpark.app;

import com.example.amusementpark.model.attraction.Attraction;
import com.example.amusementpark.model.park.Park;
import com.example.amusementpark.report.DailyReport;
import com.example.amusementpark.service.SimulationService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu
{
  private static final int WIDTH = 50;   // ширина разделителей

  private final Scanner scanner = new Scanner(System.in);

  private final Park park;
  private final SimulationService simulation;

  private DailyReport lastReport;

  public ConsoleMenu(Park park, SimulationService simulation)
  {
    if (park == null)       throw new IllegalArgumentException("Парк обязателен");
    if (simulation == null) throw new IllegalArgumentException("SimulationService обязателен");

    this.park = park;
    this.simulation = simulation;
  }

  public void run()
  {
    while (true)
    {
      printMenu();
      int choice = readInt("Ваш выбор: ");

      switch (choice)
      {
        case 1 -> showAttractions();
        case 2 -> runSimulation();
        case 3 -> showLastReport();
        case 0 -> { System.out.println("До встречи!"); return; }
        default -> System.out.println("Неверный выбор. Попробуйте снова.");
      }
    }
  }

  private void printMenu()
  {
    System.out.println();
    System.out.println("═".repeat(WIDTH));
    System.out.println("   ПАРК АТТРАКЦИОНОВ \"" + park.getName() + "\"");
    System.out.println("═".repeat(WIDTH));
    System.out.println("   1. Показать аттракционы");
    System.out.println("   2. Запустить симуляцию дня");
    System.out.println("   3. Показать отчёт за день");
    System.out.println("   0. Выход");
    System.out.println("─".repeat(WIDTH));
  }

  private int readInt(String prompt)
  {
    System.out.print(prompt);
    String line = scanner.nextLine().trim();
    try
    {
      return Integer.parseInt(line);
    }
    catch (NumberFormatException e)
    {
      return -1;
    }
  }

  private void showAttractions()
  {
    List<Attraction> attractions = park.getAllAttractions();

    if (attractions.isEmpty())
    {
      System.out.println("В парке пока нет аттракционов.");
      return;
    }

    System.out.println();
    System.out.println("═".repeat(WIDTH));
    System.out.println("   АТТРАКЦИОНЫ ПАРКА");
    System.out.println("═".repeat(WIDTH));

    for (int i = 0; i < attractions.size(); i++)
    {
      Attraction a = attractions.get(i);
      System.out.println();
      System.out.println((i + 1) + ". " + a.getType());
      System.out.printf ("   Цена билета:            %d руб.%n", a.getPrice());
      System.out.printf ("   Экстремальность:        %d/10%n", a.getExtremity());
      System.out.printf ("   Мин. рост:              %d см%n", a.getMinHeight());
      System.out.printf ("   Мин. вес:               %d кг%n", a.getMinWeight());
      System.out.printf ("   Длительность:           %d мин%n", a.getRideDuration());
      System.out.printf ("   Работает:               %d:00 – %d:00%n", a.getOpenHour(), a.getCloseHour());
      System.out.printf ("   Пропускная способность: %d чел/час%n", (int) a.getThroughput());
      System.out.printf ("   Статус:                 %s%n", a.getStatus());
    }

    System.out.println();
    System.out.println("─".repeat(WIDTH));
  }

  private void runSimulation()
  {
    System.out.println();
    System.out.println("═".repeat(WIDTH));
    System.out.println("   ЗАПУСК СИМУЛЯЦИИ ДНЯ");
    System.out.println("═".repeat(WIDTH));

    int count = readInt("   Сколько посетителей? ");
    if (count <= 0)
    {
      System.out.println("   Количество должно быть положительным.");
      return;
    }

    int open = readInt("   Час открытия (0-23)? ");
    if (open < 0 || open > 23)
    {
      System.out.println("   Некорректный час открытия.");
      return;
    }

    int close = readInt("   Час закрытия (0-23)? ");
    if (close < 0 || close > 23 || close == open)
    {
      System.out.println("   Некорректный час закрытия.");
      return;
    }

    System.out.println("   Симуляция запущена...");
    lastReport = simulation.runDay(count, open, close);
    System.out.println("   Готово! Отчёт сохранён (пункт 3).");
  }

  private void showLastReport()
  {
    if (lastReport == null)
    {
      System.out.println("Отчёта пока нет. Запустите симуляцию (пункт 2).");
      return;
    }

    DailyReport r = lastReport;

    System.out.println();
    System.out.println("═".repeat(WIDTH));
    System.out.println("   ОТЧЁТ ЗА ДЕНЬ");
    System.out.println("═".repeat(WIDTH));
    System.out.printf("   Посетителей пришло:    %5d%n", r.getVisitorsCame());
    System.out.printf("   Билетов продано:       %5d%n", r.getTicketsSold());
    System.out.printf("   Отказано в кассе:      %5d%n", r.getDeniedInCashier());
    System.out.printf("   Поездок совершено:     %5d%n", r.getRidesCompleted());
    System.out.printf("   Отказано по допуску:   %5d%n", r.getDeniedByAccess());
    System.out.printf("   Выручка:            %7d руб.%n", r.getTotalRevenue());
    System.out.println("─".repeat(WIDTH));
  }
}
