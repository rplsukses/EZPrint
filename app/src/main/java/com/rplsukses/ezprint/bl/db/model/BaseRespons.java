package com.rplsukses.ezprint.bl.db.model;

public class BaseRespons {
    private boolean error;
    private String message;

    public BaseRespons() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
