package com.barbie.haircut.api;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class ResponseResult implements Serializable {
    private String resultCode;
    private String resultMsg;
    private Object resultData;

    public ResponseResult() {
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultData() {
        return this.resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
