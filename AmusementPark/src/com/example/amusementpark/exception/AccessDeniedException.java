package com.example.amusementpark.exception;

public class AccessDeniedException extends RuntimeException
{
  public AccessDeniedException(String msg)
  {
    super(msg);
  }
}
