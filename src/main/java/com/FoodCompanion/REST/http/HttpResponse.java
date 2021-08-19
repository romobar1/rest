package com.FoodCompanion.REST.http;
import org.springframework.http.HttpStatus;

public class HttpResponse {
    private int HttpStatusCode;
    private HttpStatus HttpStatus;
    private String reason;
    private String message;

    public HttpResponse(int HttpStatusCode ,HttpStatus httpStatus, String reason, String message) {
        this.HttpStatusCode = HttpStatusCode;
        this.HttpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
    public HttpResponse() {

    }

    public HttpStatus getHttpStatus() {
        return HttpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        HttpStatus = httpStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatusCode() {
        return HttpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        HttpStatusCode = httpStatusCode;
    }
}
