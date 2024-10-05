package com.example.spring_rest.entity;

// POJO to hold error information
public class Error {
    /* Fields */

    private int status;
    private String message;
    private long timeStamp;
    // add further information you want to return to client, if you want.

    /* Constructor */

    public Error() {} // to can create instance without fill fields

    public Error(long timeStamp, int status, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
    }

    /* Getters */

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public long getTimeStamp() { return timeStamp; }

    /* Setters */
    public void setStatus(int status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
    public void setTimeStamp(long timeStamp) { this.timeStamp = timeStamp; }
}
