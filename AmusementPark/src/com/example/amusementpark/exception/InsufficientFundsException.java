package com.example.amusementpark.exception;

public class InsufficientFundsException extends RuntimeException
{
  public InsufficientFundsException(String msg)
  {
    super(msg);
  }
}
