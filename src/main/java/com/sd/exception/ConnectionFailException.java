package com.sd.exception;

/**
 * Created by sdahal on 9/23/2016.
 */
public class ConnectionFailException extends RuntimeException
{
    public ConnectionFailException (String message)
    {
        super(message);
    }
}
