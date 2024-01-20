package com.barbie.haircut.api;

import com.barbie.haircut.api.constant.Constant;
import com.barbie.haircut.api.constant.Result;

public class BaseController {

    protected VersionResponseResult setResult(Result result) {
        return setResult(result, null);

    }

    protected VersionResponseResult setResult(Result result, Object obj) {
        VersionResponseResult resResult = new VersionResponseResult();


        resResult.setResultCode(Integer.toString(result.getCode()));
        resResult.setResultMsg(result.getMessage());
        resResult.setApiVersion(Constant.api_version);
        resResult.setWebVersion(Constant.web_version);
        resResult.setResultData(obj);

        return resResult;
    }
}
