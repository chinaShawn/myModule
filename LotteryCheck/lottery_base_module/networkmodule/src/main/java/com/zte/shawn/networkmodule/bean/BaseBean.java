package com.zte.shawn.networkmodule.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 10192984 on 2017/3/21.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseBean{
    protected String errorInfo;
    protected String errorCode;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
