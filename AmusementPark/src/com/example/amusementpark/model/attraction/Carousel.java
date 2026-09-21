package com.example.amusementpark.model.attraction;

public class Carousel extends Attraction
{
  private int hoursesCount;
  private String music;

  public Carousel(int price, int capacity, int extremity, int rideDuration, int minHeight, int minWeight,
    int openHour, int closeHour, AttractionStatus status, int hoursesCount, String music)
  {
    super(price, capacity, extremity, rideDuration, minHeight, minWeight, openHour, closeHour, status);

    if (hoursesCount <= 0)
    {
      throw new IllegalArgumentException("Количество лошадок должно быть положительным");
    }

    if (music == null || music.isBlank())
    {
      throw new IllegalArgumentException("Музыка не может быть пустой строкой");
    }

    this.hoursesCount = hoursesCount;
    this.music = music;
  }

  public int getHoursesCount()
  {
    return this.hoursesCount;
  }

  public String getMusic()
  {
    return this.music;
  }

  @Override
  public String getType()
  {
    return "Карусель с лошадками";
  }

  @Override
  public double getThroughput()
  {
    int totalCycle = rideDuration + 1;
    return capacity * (60.0 / totalCycle);
  }
}
