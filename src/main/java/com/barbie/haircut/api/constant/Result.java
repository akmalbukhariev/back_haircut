package com.barbie.haircut.api.constant;

public enum Result {
    SUCCESS(100, "Success."),


    TOKEN_INVALID(200, "Invalid token information."),
    TOKEN_EXPIRED_TIME(201, "This token is expired."),
    TOKEN_UNSUPPORTED_JWT(202, "Unsupported token information."),


    LOGIN_INVALID_TOKEN(250, "Token information cannot be verified."),

    AUTHENTICATION_ERROR(300, "Your authentication information cannot be verified."),
    SERVER_ERROR(301, "A system error has occurred. Please contact your administrator.");

    private int code;
    private String message;
    private Result(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public String getCodeToString() {
        return Integer.toString(this.code);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Result getResult(int code) {
        for(Result result : this.values()){
            if(result.getCode() == code){
                return result;
            }
        }
        return null;
    }
}
