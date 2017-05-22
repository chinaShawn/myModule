package com.zte.shawn.networkmodule.MyException;

/**
 * Created by 10192984 on 2017/3/21.
 */
public class ServiceException extends Throwable {
    public int errorCode;


    public ServiceException(String detailMessage) {
        super(detailMessage);
    }
    public ServiceException(String detailMessage, int errorCode) {
        super(detailMessage);
        this.errorCode = errorCode;
    }

}
